package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View for the MainMenu.
 */

public final class MainMenu extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 20;
    private static final int BOX_X_COORDINATE = 370;
    private static final int BOX_Y_COORDINATE = 350;
    private final VBox menuBox;
    private int currentItem;
    private final MenuItem itemNewGame = new MenuItem("NEW GAME");
    private final MenuItem itemRanking = new MenuItem("RANKING");
    private final MenuItem itemSettings = new MenuItem("SETTINGS");
    private final MenuItem itemExit = new MenuItem("EXIT");

    /**
     * Constructor for MainMenu.
     *
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param pane
     *          The root for the scene
     * @param scene
     *          The scene displayed in the stage
     */
    public MainMenu(final Stage primaryStage, final PrincipalController controller,
                    final AnchorPane pane, final Scene scene) {
        super(primaryStage, controller, pane, scene);

        this.currentItem = 0;

        this.menuBox = new VBox(SPACE_BETWEEN_ITEM,
                                this.itemNewGame,
                                this.itemRanking,
                                this.itemSettings,
                                this.itemExit);

        //it intercepts the button presses
        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && this.currentItem > 0) {
                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(--this.currentItem).setActive(true);
            }

            if (event.getCode() == KeyCode.DOWN
                && this.currentItem < this.menuBox.getChildren().size() - 1) {

                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(++this.currentItem).setActive(true);

            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                getMenuItem(this.currentItem).activate();
            }
        });

        itemActions();

        this.menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box
        if (ResolutionUtil.isFullScreen()) {
            this.menuBox.setLayoutX(BOX_X_COORDINATE * ResolutionUtil.getWidth() / ResolutionUtil.SMALL_WIDTH);
            this.menuBox.setLayoutY(BOX_Y_COORDINATE * ResolutionUtil.getHeight() / ResolutionUtil.SMALL_HEIGHT);
        } else {
            this.menuBox.setLayoutX(BOX_X_COORDINATE);
            this.menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(this.currentItem).setActive(true);

        final AnchorPane root = getRoot();

        root.getChildren().clear();
        pane.getChildren().add(this.menuBox);

        pane.setId("mainMenu");

        getScene().setRoot(getRoot());
    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) this.menuBox.getChildren().get(index);
    }

    @Override
    protected void itemActions() {
        this.itemNewGame.setOnActivate(() -> {
            setPlayerInputHandler();
            getAudioPlayer().stopMusic();
            getController().showGameField(getScene());
            checkResolution();
            clearEnter();
        });
        this.itemRanking.setOnActivate(() -> {
            getController().showRankingView(getViewFactory());
        });
        this.itemSettings.setOnActivate(() -> {
            getController().showSettings(getViewFactory());
        });
        this.itemExit.setOnActivate(() -> {
            getController().stopGame();
            System.exit(0);
        });
    }

    /**
     * it clears the actions of the button enter.
     */
    private void clearEnter() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            }
        });
    }

    /**
     * Method used to set the PlayerInputHandler in the PrincipalController.
     */
    private void setPlayerInputHandler() {
        getController().setPlayerInputHandler(new PlayerInputHandler(getScene()));
    }
}
