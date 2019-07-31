package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * @author Manuel
 * Scene for the MainMenu.
 */

public class MainMenu extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 370;
    private static final int BOX_Y_COORDINATE = 350;
    private final AnchorPane pane;
    private VBox menuBox;
    private int currentItem = 0;
    private final MenuItem itemNewGame = new MenuItem("NEW GAME");
    private final MenuItem itemScore = new MenuItem("SCORE");
    private final MenuItem itemSettings = new MenuItem("SETTINGS");
    private final MenuItem itemExit = new MenuItem("EXIT");

    public MainMenu(final Stage primaryStage, final PrincipalController controller, final AudioPlayer audioPlayer) {
        super(primaryStage,controller, audioPlayer);

        //it intercepts the button presses
        this.setOnKeyPressed(event -> {
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

        pane = new AnchorPane();

        menuBox = new VBox(SPACE_BETWEEN_ITEM,
                itemNewGame,
                itemScore,
                itemSettings,
                itemExit);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(0).setActive(true);

        pane.getChildren().add(menuBox);

        pane.setId("mainMenu");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    /**
     * Method used to set the action for each button.
     */
    @Override
     protected void buttonActions() {
        //Da togliere i commenti per usare gli altri pulsanti
        itemNewGame.setOnActivate(() -> {
            getPrimaryStage().setScene(getViewFactory().createGameOver());
            checkResolution();
            getAudioPlayer().stopMusic();
        });
        itemScore.setOnActivate(() -> {
            getPrimaryStage().setScene(getViewFactory().createRankingView());
            checkResolution();
        });
        itemSettings.setOnActivate(() -> {
            getPrimaryStage().setScene(getViewFactory().createSettingsMenu());
            checkResolution();
        });
        itemExit.setOnActivate(() -> {
            getController().stopGame();
            System.exit(0);
        });
    }
}
