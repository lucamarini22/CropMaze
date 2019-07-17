package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Manuel
 * Scene for the settings Menu used to set the Resolution
 */

public class SettingsMenu extends Scene {

    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 315;
    private static final int BOX_Y_COORDINATE = 300;
    private static Stage primaryStage;
    private final AnchorPane pane;

    private VBox menuBox;
    private int currentItem = 0;
    private final MenuItem itemSmallScreen = new MenuItem("Small Screen");
    private final MenuItem itemFullScreen = new MenuItem("Full Screen");
    private final MenuItem itemBack = new MenuItem("BACK");

    public SettingsMenu() {
        super(new AnchorPane(), Resolution.getWidth(), Resolution.getHeight());

        //it intercepts the button presses
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(currentItem).activate();
            }
        });

        pane = new AnchorPane();

        menuBox = new VBox(SPACE_BETWEEN_ITEM,
                itemSmallScreen,
                itemFullScreen,
                itemBack);

        buttonActions();

        if (Resolution.isFullScreen()) {
            itemSmallScreen.setUnderline(false);
            itemFullScreen.setUnderline(true);
        } else {
            itemSmallScreen.setUnderline(true);
            itemFullScreen.setUnderline(false);
        }

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

        pane.setId("settingsMenu");
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
    private void buttonActions() {
        itemBack.setOnActivate(() -> {
            this.primaryStage.setScene(MainMenu.getMainMenu(primaryStage));
            checkResolution();
    });
        itemSmallScreen.setOnActivate(() -> {
            Resolution.setSmallResolution();
            itemSmallScreen.setUnderline(true);
            itemFullScreen.setUnderline(false);
        });
        itemFullScreen.setOnActivate(() -> {
            Resolution.setFullResolution();
            itemSmallScreen.setUnderline(false);
            itemFullScreen.setUnderline(true);
        });
    }

    /**
     * Method used to set or not the stage to FuLLScreen
     */
    private void checkResolution(){
        if(Resolution.isFullScreen()){
            this.primaryStage.setFullScreen(true);
        }
        else{
            this.primaryStage.setFullScreen(false);
        }
    }

    /**
     * Getter for the Scene.
     * @param stage
     * @return SettingsMenu
     */
    public static SettingsMenu getSettingsMenu(final Stage stage) {
        primaryStage = stage;
        primaryStage.centerOnScreen();
        return new SettingsMenu();
    }
}