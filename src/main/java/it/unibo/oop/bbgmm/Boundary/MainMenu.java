package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class MainMenu extends Scene {
    private static double width;
    private static double height;
    private static Stage primaryStage;

    Button exitButton = new Button("EXIT");
    Button newGameButton = new Button("NEW GAME");
    Button scoreButton = new Button("SCORE");
    Button resolutionButton = new Button("RESOLUTION");

    private VBox menuBox;
    private int currentItem = 0;

    public MainMenu() {
        super(new AnchorPane());
        setResolution();
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getButton(currentItem).setDisable(false);
                    getButton(--currentItem).setDisable(true);
                }
            }
            if (e.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getButton(currentItem).setDisable(false);
                    getButton(++currentItem).setDisable(true);
                }
            }
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Fare cose");
                //fa qualcosa in base al bottone quando premo enter
            }
        });
    }

    private void createBox(){
        menuBox = new VBox(10,
                newGameButton,
                scoreButton,
                resolutionButton,
                exitButton);

        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(360);
        menuBox.setTranslateY(300);
    }

    private Button getButton(int index) {

        return (Button) menuBox.getChildren().get(index);

    }

    public static MainMenu getMainMenu(Stage stage){
        primaryStage = stage;
        setResolution();
        return new MainMenu();
    }

    private static void setResolution(){
        height= Resolution.getHeight();
        width=Resolution.getWidth();
    }

}
