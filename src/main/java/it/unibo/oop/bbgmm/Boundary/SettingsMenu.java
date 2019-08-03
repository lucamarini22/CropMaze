package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import it.unibo.oop.bbgmm.Utilities.Volume;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.MENU_TRACK;

/**
 * @author Manuel
 * Scene for the settings Menu used to set the Resolution
 */

public class SettingsMenu extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 10;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 200;
    private static final int BOX_Y_COORDINATE = 185;

    private VBox menuBox;
    private int currentItem = 0;
    private Volume musicVolume = getController().getVolumeData().getMusicVolume();
    private Volume effectsVolume = getController().getVolumeData().getEffectsVolume();
    private final MenuItem itemSmallScreen = new MenuItem("Small Screen");
    private final MenuItem itemFullScreen = new MenuItem("Full Screen");
    private MenuItem itemMusicVolume = new MenuItem("Music: " + musicVolume.getText());
    private MenuItem itemEffectsVolume = new MenuItem("Effects: " + effectsVolume.getText());
    private final MenuItem itemBack = new MenuItem("BACK");

    public SettingsMenu(final Stage primaryStage, final PrincipalController controller,
                        final AnchorPane pane, final Scene scene) {
        super(primaryStage, controller, pane, scene);


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
                itemSmallScreen,
                itemFullScreen,
                itemMusicVolume,
                itemEffectsVolume,
                itemBack);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box and witch item is underlined
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
            itemSmallScreen.setUnderline(false);
            itemFullScreen.setUnderline(true);
        }
        else {
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
            itemSmallScreen.setUnderline(true);
            itemFullScreen.setUnderline(false);
        }

        getMenuItem(0).setActive(true);

        AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(menuBox);

        root.setId("settingsMenu");

    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    @Override
    protected void buttonActions() {
        itemBack.setOnActivate(() -> {
            checkResolution();
            getController().showMainMenu(getViewFactory());
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

        itemMusicVolume.setOnActivate(() -> {
            nextMusicVolume();
            updateVolume(this.musicVolume,this.effectsVolume);
            getAudioPlayer().stopMusic();
            getAudioPlayer().playMusic(MENU_TRACK.getPath());
            getViewFactory().createSettingsMenu();
        });

        itemEffectsVolume.setOnActivate(() -> {
            nextEffectsVolume();
            updateVolume(this.musicVolume,this.effectsVolume);
            getViewFactory().createSettingsMenu();
        });
    }

    private void nextMusicVolume(){
        this.musicVolume = Volume.values()[(this.musicVolume.ordinal() + 1)% Volume.values().length];
    }

    private void nextEffectsVolume(){
        this.effectsVolume = Volume.values()[(this.effectsVolume.ordinal() + 1)% Volume.values().length];
    }
}