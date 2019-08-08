package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * View for the MainMenu.
 */

public class MainMenu extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int BOX_X_COORDINATE = 370;
    private static final int BOX_Y_COORDINATE = 350;
    private VBox menuBox;
    private int currentItem = 0;
    private final MenuItem itemNewGame = new MenuItem("NEW GAME");
    private final MenuItem itemScore = new MenuItem("SCORE");
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
        super(primaryStage,controller, pane, scene);

        //it intercepts the button presses
        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                getMenuItem(currentItem).activate();
            }
        });

        menuBox = new VBox(SPACE_BETWEEN_ITEM,
                itemNewGame,
                itemScore,
                itemSettings,
                itemExit);

        itemActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(currentItem).setActive(true);

        AnchorPane root = getRoot();

        root.getChildren().clear();
        pane.getChildren().add(menuBox);

        pane.setId("mainMenu");

        getScene().setRoot(getRoot());
    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    @Override
    protected void itemActions() {
        itemNewGame.setOnActivate(() -> {
            setPlayerInputHandler();
            getAudioPlayer().stopMusic();
            getController().showGameField(getScene());
            checkResolution();
            clearEnter();
        });
        itemScore.setOnActivate(() -> {
            getController().showGameOver(getViewFactory());
        });
        itemSettings.setOnActivate(() -> {
            getController().showSettings(getViewFactory());
        });
        itemExit.setOnActivate(() -> {
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
    private void setPlayerInputHandler(){
        getController().setPlayerInputHandler(new PlayerInputHandler(getScene()));
    }
}
