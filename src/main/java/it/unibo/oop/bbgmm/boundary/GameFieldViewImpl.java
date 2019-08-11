package it.unibo.oop.bbgmm.boundary;


import it.unibo.oop.bbgmm.control.EndLevelController;
import it.unibo.oop.bbgmm.control.PlayerInputListener;
import it.unibo.oop.bbgmm.control.PrincipalController;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import static it.unibo.oop.bbgmm.boundary.Music.GAME_TRACK;
import static it.unibo.oop.bbgmm.boundary.Music.BUTTON_PRESS;


/**
 * Implementation of the view of {@link it.unibo.oop.bbgmm.entity.GameField}.
 */
public final class GameFieldViewImpl implements GameFieldView {

    private static final int TOP_LEFT_POINT_BACKGROUND = -800;
    private static final int SPACING = 20;
    private static final String BACKGROUND_PATH = "/images/background.png";
    private final Stage primaryStage;
    private final Group fieldView = new Group();
    private final Group rootView = new Group(fieldView);
    private final AudioPlayer audioplayer;
    private final PlayerInputHandler playerInputHandler;
    private final StatusBarScreen statusBar = new StatusBarImpl();
    private ImageView background;
    private final Button upgradeButton = new Button("UPGRADE");
    private final PrincipalController principalController;
    private EndLevelController endLevelController;
    /**
     * Constructor of {@link GameFieldViewImpl}.
     * @param audioPlayer
     *      {@link AudioPlayer}
     * @param playerInputHandler
     *      {@link PlayerInputHandler} instance
     * @param principalController
     *      {@link PrincipalController} instance
     * @param primaryStage
     *      The primary {@link Stage}
     */
    public GameFieldViewImpl(final AudioPlayer audioPlayer, final PlayerInputHandler playerInputHandler,
                             final PrincipalController principalController, final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.audioplayer = audioPlayer;
        this.playerInputHandler = playerInputHandler;
        this.setBackground();
        fieldView.getChildren().add(this.background);
        rootView.getChildren().add(new HBox(SPACING, statusBar.getStatusBox(), upgradeButton));
        this.principalController = principalController;
        this.primaryStage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, this::onPress);
        this.audioplayer.playMusic(GAME_TRACK.getPath());
    }

    @Override
    public EntityViewFactory getEntityViewFactory() {
        return new EntityViewFactoryImpl(this.fieldView, this.audioplayer, this.statusBar);
    }

    @Override
    public void showField(final TileLayer layer, final Point2D topLeft, final Dimension2D tileSize) {
        for (int x = 0; x < layer.getMap().getWidth(); x++) {
            for (int y = 0; y < layer.getMap().getHeight(); y++) {
                final Tile tile = layer.getTileAt(x, y);
                if (tile != null && tile.getImage() != null) {
                    final Image tileImage = SwingFXUtils.toFXImage(tile.getImage(), null);
                    final ImageView tileView = new ImageView(tileImage);
                    tileView.setFitWidth(ViewUtils.metersToPixels(tileSize.getWidth()));
                    tileView.setFitHeight(ViewUtils.metersToPixels(tileSize.getHeight()));
                    tileView.setTranslateX(x * ViewUtils.metersToPixels(tileSize.getWidth()) + topLeft.getX());
                    tileView.setTranslateY(y * ViewUtils.metersToPixels(tileSize.getHeight()) + topLeft.getY());
                    fieldView.getChildren().add(tileView);
                }
            }
        }
    }

    @Override
    public Group getGroup() {
        return this.rootView;
    }

    @Override
    public void setPlayerInputListener(final PlayerInputListener playerInputListener) {
        this.playerInputHandler.setListener(playerInputListener);
    }

    /**
     * Sets an image of background.
     */
    private void setBackground() {
        this.background = new ImageView(new Image(BACKGROUND_PATH));
        this.background.setX(TOP_LEFT_POINT_BACKGROUND);
        this.background.setY(TOP_LEFT_POINT_BACKGROUND);
    }

    /**
     * @return the Upgrade Button
     */
    public Button getUpgradeButton() {
        return this.upgradeButton;
    }
    /**
     * When a button is pressed.
     * @param event
     *          The event triggered when a button is pressed
     */
    private void onPress(final KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            this.audioplayer.playSound(BUTTON_PRESS.getPath());
            showPauseBox(this.principalController);
        }
    }

    /**
     * Shows the PauseBox.
     * @param principalController
     */
    private void showPauseBox(final PrincipalController principalController) {
        principalController.getGameController().get().stop();
        boolean answer = new PauseBox(this.audioplayer).display(this.primaryStage);
        if (answer) {
            this.audioplayer.stopMusic();
            principalController.resetGame();
        } else {
            principalController.getGameController().get().start();
        }
    }


    @Override
    public void showEndLevelBox(final PrincipalController principalController) {
        this.playerInputHandler.clearInput();
        principalController.getGameController().get().stop();
        final EndLevelView endLevelView = new EndLevelView(this.audioplayer);
        endLevelView.setObserver(this.endLevelController);
        endLevelView.display(this.primaryStage);
    }

    @Override
    public void setEndLevelController(final EndLevelController endLevelController) {
        this.endLevelController = endLevelController;
    }
}
