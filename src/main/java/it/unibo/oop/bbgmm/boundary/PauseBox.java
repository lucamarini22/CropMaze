package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.utilities.FontMakerUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static it.unibo.oop.bbgmm.boundary.Music.BUTTON_PRESS;
import static it.unibo.oop.bbgmm.boundary.Music.BUTTON_SWITCH;

/**
 * Class used to return to the mainMenu during the game.
 */
public class PauseBox {

    private static final String MESSAGE = "ATTENTION!\nDo you want to go back to the main menu?\n(All progress will be erased)";
    private static final int SIZE_MESSAGE = 35;
    private static final int SIZE_ITEMS = 40;
    private static final int SPACING = 40;
    private static final int POS = 33;
    private static final int SPACE_BETWEEN_HORIZONTAL_ITEM = 50;
    private static final int SPACE_BETWEEN_VERTICAL_ITEM = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 230;
    private boolean answer = false;
    private final AudioPlayer audioPlayer;
    private int currentItem = 1;
    private HBox itemLayout;

    public PauseBox(final AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    /**
     * It displays the PauseBox.
     *
     * @param primaryStage
     *          The principal stage
     * @return the answer
     *          The answer of the player
     */
    public boolean display(final Stage primaryStage) {
        final Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.UNDECORATED);


        final Label label = new Label(MESSAGE);
        label.setFont(FontMakerUtil.getSizedFont(SIZE_MESSAGE));
        label.setEffect(new GaussianBlur(2));
        label.setTextFill(Color.FORESTGREEN);

        final MenuItem itemYes = new MenuItem("Yes");
        final MenuItem itemNo = new MenuItem("No");

        itemYes.setFont(SIZE_ITEMS);
        itemNo.setFont(SIZE_ITEMS);

        final VBox layout = new VBox(SPACE_BETWEEN_VERTICAL_ITEM);
        this.itemLayout = new HBox(SPACE_BETWEEN_HORIZONTAL_ITEM);

        this.itemLayout.getChildren().addAll(itemYes, itemNo);
        this.itemLayout.setSpacing(SPACING);
        this.itemLayout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, this.itemLayout);
        layout.setAlignment(Pos.CENTER);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(layout);
        layout.setLayoutX(POS);
        layout.setLayoutY(POS);
        pane.setId("pauseBox");

        final Scene scene = new Scene(pane);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT && this.currentItem == 0) {
                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(++this.currentItem).setActive(true);
                this.answer = false;
            }

            if (event.getCode() == KeyCode.LEFT && this.currentItem != 0) {
                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(--this.currentItem).setActive(true);
                answer = true;
            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                stage.close();
            }
        });

        getMenuItem(this.currentItem).setActive(true);

        scene.getStylesheets().add("Style.css");

        stage.setScene(scene);
        stage.showAndWait();

        return this.answer;
    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) this.itemLayout.getChildren().get(index);
    }

    /**
     * Method called to play the buttonSwitch sound.
     */
    private void playSwitchSound(){
        this.audioPlayer.playSound(BUTTON_SWITCH.getPath());
    }

    /**
     * Method called to play the buttonSwitch sound.
     */
    private void playPressSound(){
        this.audioPlayer.playSound(BUTTON_PRESS.getPath());
    }
}
