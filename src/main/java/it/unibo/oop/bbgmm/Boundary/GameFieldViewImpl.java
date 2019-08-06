package it.unibo.oop.bbgmm.Boundary;


import it.unibo.oop.bbgmm.Control.PlayerInputListener;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import static it.unibo.oop.bbgmm.Boundary.Music.*;

/**
 * Implementation of the view of {@link it.unibo.oop.bbgmm.Entity.GameField}.
 */
public final class GameFieldViewImpl implements GameFieldView {
    private static final int TOP_LEFT_POINT_BACKGROUND = -800;
    private static final String BACKGROUND_PATH = "/images/background.png";
    private final Group fieldView = new Group();
    private final Group rootView = new Group(fieldView);
    private final AudioPlayer audioplayer;
    private final PlayerInputHandler playerInputHandler;
    private final StatusBarScreen statusBar = new StatusBarImpl();
    private ImageView background;
    private final PauseBox pauseBox;
    private final PrincipalController principalController;

    /**
     * Constructor of {@link GameFieldViewImpl}.
     * @param audioPlayer
     *      {@link AudioPlayer}
     * @param playerInputHandler
     *      {@link PlayerInputHandler} instance
     */
    public GameFieldViewImpl(final AudioPlayer audioPlayer, final PlayerInputHandler playerInputHandler,
                             final PrincipalController principalController, final Scene scene) {
        this.audioplayer = audioPlayer;
        this.playerInputHandler = playerInputHandler;
        this.setBackground();
        fieldView.getChildren().add(this.background);
        rootView.getChildren().add(statusBar.getStatusBox());
        this.pauseBox = new PauseBox(this.audioplayer);
        this.principalController = principalController;

        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::onPress);

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
     * When a button is pressed
     * @param event
     *          The event triggered when a button is pressed
     */
    private void onPress(final KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE)){
            showPauseBox(this.principalController);
            this.audioplayer.playSound(BUTTON_PRESS.getPath());
        }
    }

    /**
     * Shows the PauseBox
     * @param principalController
     */
    private void showPauseBox(final PrincipalController principalController) {
        principalController.getGameController().get().stop();
        boolean answer = pauseBox.display("ATTENTION !", "Do you want to go back to the main menu?");
        if(answer){
            this.audioplayer.stopMusic();
            principalController.resetGame();
        }
        else{
            principalController.getGameController().get().start();
        }
    }
}
