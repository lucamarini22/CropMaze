package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.FontMaker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_PRESS;
import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_SWITCH;

public class PauseBox {

    private static final int FONT_SIZE = 40;
    private static final int SPACING = 40;
    private static final int INSETS = 8;
    private static final int SPACE_BETWEEN_HORIZONTAL_ITEM = 50;
    private static final int SPACE_BETWEEN_VERTICAL_ITEM = 10;
    private static final double MIN_WIDTH = 350;
    private boolean answer = false;
    private final AudioPlayer audioPlayer;
    private int currentItem = 1;
    private HBox itemLayout;

    public PauseBox(final AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    /**
     * It displays the MessageBox.
     *
     * @param title
     *            The title of the MessageBox.
     * @param message
     *            The message inside the MessageBox..
     * @return The choice of the user where true equals yes and false equals no.
     */
    public boolean display(final String title, final String message) {
        final Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(MIN_WIDTH);

        final Label label = new Label(message);
        label.setFont(FontMaker.getSizedFont(FONT_SIZE));

        final MenuItem itemYes = new MenuItem("Yes");
        final MenuItem itemNo = new MenuItem("No");

        itemYes.setFont(FONT_SIZE);
        itemNo.setFont(FONT_SIZE);

        final VBox layout = new VBox(SPACE_BETWEEN_VERTICAL_ITEM);
        this.itemLayout = new HBox(SPACE_BETWEEN_HORIZONTAL_ITEM);

        this.itemLayout.getChildren().addAll(itemYes, itemNo);
        this.itemLayout.setSpacing(SPACING);
        this.itemLayout.setPadding(new Insets(INSETS));
        this.itemLayout.setAlignment(Pos.CENTER);

        layout.setMinWidth(MIN_WIDTH);
        layout.getChildren().addAll(label, this.itemLayout);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (this.currentItem == 0) {
                    playSwitchSound();
                    getMenuItem(this.currentItem).setActive(false);
                    getMenuItem(++this.currentItem).setActive(true);
                    this.answer = false;
                }
            }

            if (event.getCode() == KeyCode.LEFT) {
                if (this.currentItem != 0) {
                    playSwitchSound();
                    getMenuItem(this.currentItem).setActive(false);
                    getMenuItem(--this.currentItem).setActive(true);
                    answer = true;
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                stage.close();
            }
        });

        getMenuItem(this.currentItem).setActive(true);

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
     * Method called to play the buttonSwitch sound
     */
    private void playSwitchSound(){
        this.audioPlayer.playSound(BUTTON_SWITCH.getPath());
    }

    /**
     * Method called to play the buttonSwitch sound
     */
    private void playPressSound(){
        this.audioPlayer.playSound(BUTTON_PRESS.getPath());
    }


}
