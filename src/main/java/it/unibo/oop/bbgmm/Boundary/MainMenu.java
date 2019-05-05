package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Manuel
 */

public class MainMenu extends Scene {

    private static Stage primaryStage;
    private final AnchorPane pane;

    private VBox menuBox;
    private int currentItem = 0;
    private final MenuItem itemNewGame = new MenuItem("NEW GAME");
    private final MenuItem itemScore = new MenuItem("SCORE");
    private final MenuItem itemSettings = new MenuItem("SETTINGS");
    private final MenuItem itemExit = new MenuItem("EXIT");

    //private MediaPlayer buttonClicked;

    public MainMenu() {
        super(new AnchorPane(), Resolution.getSmallWidth(), Resolution.getSmallHeight());

        //createMusicPlayer();

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

        menuBox = new VBox(25,
                itemNewGame,
                itemScore,
                itemSettings,
                itemExit);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(365);
        menuBox.setTranslateY(350);

        getMenuItem(0).setActive(true);

        pane.getChildren().add(menuBox);

        pane.setId("mainMenu");

        this.setRoot(pane);

        this.getStylesheets().add("Style.css");
        }

    public static MainMenu getMainMenu(Stage stage) {
        primaryStage = stage;
        primaryStage.setHeight(Resolution.getSmallHeight());
        primaryStage.setWidth(Resolution.getSmallWidth());
        return new MainMenu();
    }

    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    //private void createMusicPlayer(){
    //    this.buttonClicked = new MediaPlayer(new Media("/res/sounds/button_clicked.mp3"));
    //    buttonClicked.play();
    //}

    private void buttonActions(){
        //Da togliere i commenti per usare gli altri pulsanti
        //itemNewGame.setOnActivate(() -> {
        //    this.primaryStage.setScene(GameFieldView.getGameFieldView(this.primaryStage));
        //});
        //itemScore.setOnActivate(() -> {
        //    this.primaryStage.setScene(Ranking.getRanking(this.primaryStage));
        //});
        itemSettings.setOnActivate(() -> {
            this.primaryStage.setScene(SettingsMenu.getSettingsMenu(this.primaryStage));
        });
        itemExit.setOnActivate(() -> System.exit(0));
    }
}
