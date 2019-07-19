package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOver extends Scene {
    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 365;
    private static final int BOX_Y_COORDINATE = 350;
    private final PrincipalController controller;
    private static Stage gameOverStage;
    private final AnchorPane pane;

    private int currentItem = 0;
    private VBox menuBox;
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");
    private final MenuItem itemExit = new MenuItem("EXIT");

    public GameOver(final PrincipalController controller){
        super(new AnchorPane(), Resolution.getWidth(), Resolution.getHeight());
        this.controller = controller;

        this.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP) {
                if(currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.DOWN) {
                if(currentItem < menuBox.getChildren().size() - 1 ){
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.ENTER){
                getMenuItem(currentItem).activate();
            }
        });

        pane = new AnchorPane();
        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemMainMenu, itemExit);

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

        pane.setId("gameOverView");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }

    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    private void buttonActions(){
        itemMainMenu.setOnActivate(() -> {
            this.gameOverStage.setScene(MainMenu.getMainMenu(this.gameOverStage, controller));
            checkResolution();
        });

        itemExit.setOnActivate(() -> System.exit(0));
    }

    /**
     * Method used to set or not the stage to FuLLScreen
     */
    private void checkResolution(){
        if(Resolution.isFullScreen()){
            this.gameOverStage.setFullScreen(true);
        }
        else{
            this.gameOverStage.setFullScreen(false);
        }
    }

    /**
     * Getter for the scene
     * @param stage
     * @return GameOver
     */
    public static GameOver getGameOver(Stage stage, final PrincipalController controller){
        gameOverStage = stage;
        gameOverStage.centerOnScreen();
        return new GameOver(controller);
    }
}
