package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.EndLevelController;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class EndLevelView extends AbstractBasicView implements ObservableView<EndLevelController> {
    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 300;
    private static final int BOX_Y_COORDINATE = 500;
    private static final int NEXT_LEVEL_X_COORDINATE = 200;
    private static final int NEXT_LEVEL_Y_COORDINATE = 50;
    private static final double SOUND_VOLUME = 1;
    private static final double MUSIC_VOLUME = 0.4;
    private static final ImageView nextLevel = new ImageView(new Image("images/nextLevel.png"));

    private EndLevelController endLevelController;
    private int currentItem = 0;
    private VBox menuBox;
    private VBox boxImage;
    private final MenuItem itemNextLevel = new MenuItem("NEXT LEVEL");
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");

    public EndLevelView(final Stage primaryStage, final PrincipalController controller,
                    final AnchorPane pane, final Scene scene) {
        super(primaryStage, controller, pane, scene);

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

        getAudioPlayer().playMusic(Music.GAMEOVER_TRACK.getPath());

        boxImage = new VBox(nextLevel);
        boxImage.setAlignment(Pos.TOP_CENTER);
        boxImage.setTranslateX(NEXT_LEVEL_X_COORDINATE);
        boxImage.setTranslateY(NEXT_LEVEL_Y_COORDINATE);

        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemNextLevel, itemMainMenu);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box
        if (Resolution.isFullScreen()) {
            menuBox.setLayoutX(BOX_X_COORDINATE * Resolution.getWidth() / Resolution.SMALL_WIDTH + DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE * Resolution.getHeight() / Resolution.SMALL_HEIGHT);
        } else {
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(0).setActive(true);

        AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(boxImage);
        root.getChildren().add(menuBox);

        root.setId("endLevelView");
    }

    private MenuItem getMenuItem(final int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    @Override
    protected void buttonActions() {
        itemNextLevel.setOnActivate(() -> {
            this.endLevelController.goToNextLevel();
        });

        itemMainMenu.setOnActivate(() -> {
            this.endLevelController.goToMainMenu();
        });
    }

    @Override
    public void setObserver(final EndLevelController observer) {
        this.endLevelController = observer;
    }
}
