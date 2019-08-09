package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.EndLevelController;
import it.unibo.oop.bbgmm.Utilities.FontMaker;
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

import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_PRESS;

/**
 * View that it shows when a level is finished, that is when all enemies are dead.
 */
public final class EndLevelView implements ObservableView<EndLevelController> {
    private static final String MESSAGE = "Press ENTER to go to the next level\n\n\n";
    private static final int SIZE_MESSAGE = 35;
    private static final int SIZE_ITEMS = 40;
    private static final int SPACING = 40;
    private static final int POS = 33;
    private static final int SPACE_BETWEEN_HORIZONTAL_ITEM = 50;
    private static final int SPACE_BETWEEN_VERTICAL_ITEM = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 230;
    private final AudioPlayer audioPlayer;
    private HBox itemLayout;
    private EndLevelController endLevelController;

    /**
     * {@link EndLevelView} constructor.
     * @param audioPlayer
     *      {@link AudioPlayer} instance
     * @param endLevelController
     *      Controller that manages the end of a level
     */
    public EndLevelView(final AudioPlayer audioPlayer, final EndLevelController endLevelController) {
        this.audioPlayer = audioPlayer;
        //this.endLevelController = endLevelController;
    }

    /**
     * Displays the {@link EndLevelView}.
     *
     * @param primaryStage
     *          The principal stage
     */
    public void display(final Stage primaryStage) {
        int currentItem = 0;
        final MenuItem itemNextLevel = new MenuItem("Next Level");
        final Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.UNDECORATED);


        final Label label = new Label(MESSAGE);
        label.setFont(FontMaker.getSizedFont(SIZE_MESSAGE));
        label.setEffect(new GaussianBlur(2));
        label.setTextFill(Color.FORESTGREEN);
        itemNextLevel.setFont(SIZE_ITEMS);

        final VBox layout = new VBox(SPACE_BETWEEN_VERTICAL_ITEM);
        this.itemLayout = new HBox(SPACE_BETWEEN_HORIZONTAL_ITEM);
        this.itemLayout.getChildren().addAll(itemNextLevel);
        this.itemLayout.setSpacing(SPACING);
        this.itemLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, this.itemLayout);
        layout.setAlignment(Pos.CENTER);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(layout);
        layout.setLayoutX(POS);
        layout.setLayoutY(POS);
        pane.setId("endLevelView");

        final Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                stage.close();
                endLevelController.goToNextLevel();
            }
        });
        getMenuItem(currentItem).setActive(true);
        scene.getStylesheets().add("Style.css");
        stage.setScene(scene);
        stage.show();
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
    private void playPressSound() {
        this.audioPlayer.playSound(BUTTON_PRESS.getPath());
    }

    @Override
    public void setObserver(final EndLevelController observer) {
        this.endLevelController = observer;
    }
}
