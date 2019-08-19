package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import it.unibo.oop.bbgmm.utilities.Volume;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.boundary.Music.MENU_TRACK;

/**
 * View for the settings Menu.
 */
public final class SettingsMenu extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 10;
    private static final int BOX_X_COORDINATE = 200;
    private static final int BOX_Y_COORDINATE = 130;

    private final VBox menuBox;
    private int currentItem;
    private Volume musicVolume = getController().getVolumeData().getMusicVolume();
    private Volume effectsVolume = getController().getVolumeData().getEffectsVolume();
    private final MenuItem itemSmallScreen = new MenuItem("Small Screen");
    private final MenuItem itemFullScreen = new MenuItem("Full Screen");
    private final MenuItem itemMusicVolume = new MenuItem("Music: " + musicVolume.getText());
    private final MenuItem itemEffectsVolume = new MenuItem("Effects: " + effectsVolume.getText());
    private final MenuItem itemEmptyRanking = new MenuItem("Empty Ranking");
    private final MenuItem itemBack = new MenuItem("BACK");

    /**
     * Constructor for SettingsMenu.
     *
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param pane
     *          The root for the scene
     * @param scene
     *          The scene displayed in the stage
     */
    public SettingsMenu(final Stage primaryStage, final PrincipalController controller,
                        final AnchorPane pane, final Scene scene) {
        super(primaryStage, controller, pane, scene);

        this.currentItem = 0;

        this.menuBox = new VBox(SPACE_BETWEEN_ITEM,
                                this.itemSmallScreen,
                                this.itemFullScreen,
                                this.itemMusicVolume,
                                this.itemEffectsVolume,
                                this.itemEmptyRanking,
                                this.itemBack);

        //it intercepts the button presses
        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && this.currentItem > 0) {
                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(--this.currentItem).setActive(true);
            }

            if (event.getCode() == KeyCode.DOWN
                && this.currentItem < this.menuBox.getChildren().size() - 1) {
                playSwitchSound();
                getMenuItem(this.currentItem).setActive(false);
                getMenuItem(++this.currentItem).setActive(true);
            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                getMenuItem(currentItem).activate();
            }
        });

        itemActions();

        this.menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box and witch item is underlined
        if (ResolutionUtil.isFullScreen()) {
            this.menuBox.setLayoutX(BOX_X_COORDINATE * ResolutionUtil.getWidth() / ResolutionUtil.getSmallWidth());
            this.menuBox.setLayoutY(BOX_Y_COORDINATE * ResolutionUtil.getHeight() / ResolutionUtil.getSmallHeight());
            this.itemSmallScreen.setUnderline(false);
            this.itemFullScreen.setUnderline(true);
        } else {
            this.menuBox.setLayoutX(BOX_X_COORDINATE);
            this.menuBox.setLayoutY(BOX_Y_COORDINATE);
            this.itemSmallScreen.setUnderline(true);
            this.itemFullScreen.setUnderline(false);
        }

        getMenuItem(this.currentItem).setActive(true);

        final AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(this.menuBox);

        root.setId("settingsMenu");

    }

    /**
     * Method used to get the requested element of the buttons' box.
     */
    private MenuItem getMenuItem(final int index) {
        return (MenuItem) this.menuBox.getChildren().get(index);
    }

    @Override
    protected void itemActions() {
        this.itemBack.setOnActivate(() -> {
            getController().showMainMenu(getViewFactory());
        });
        this.itemSmallScreen.setOnActivate(() -> {
            if (ResolutionUtil.isFullScreen()) {
                ResolutionUtil.setSmallResolution();
                this.itemSmallScreen.setUnderline(true);
                this.itemFullScreen.setUnderline(false);
                getController().showSettings(getViewFactory());
                checkResolution();
            }
        });
        this.itemFullScreen.setOnActivate(() -> {
            if (!ResolutionUtil.isFullScreen()) {
                ResolutionUtil.setFullResolution();
                this.itemSmallScreen.setUnderline(false);
                this.itemFullScreen.setUnderline(true);
                getController().showSettings(getViewFactory());
                checkResolution();
            }
        });

        this.itemMusicVolume.setOnActivate(() -> {
            nextMusicVolume();
            updateVolume(this.musicVolume, this.effectsVolume);
            getAudioPlayer().stopMusic();
            getAudioPlayer().playMusic(MENU_TRACK.getPath());
            getViewFactory().createSettingsMenu();
        });

        this.itemEffectsVolume.setOnActivate(() -> {
            nextEffectsVolume();
            updateVolume(this.musicVolume, this.effectsVolume);
            getViewFactory().createSettingsMenu();
        });

        this.itemEmptyRanking.setOnActivate(() -> {
            getController().emptyRanking();
        });
    }

    /**
     * Setter for the next intensity of music volume.
     */
    private void nextMusicVolume() {
        this.musicVolume = Volume.values()[(this.musicVolume.ordinal() + 1) % Volume.values().length];
    }

    /**
     * Setter for the next intensity of effects volume.
     */
    private void nextEffectsVolume() {
        this.effectsVolume = Volume.values()[(this.effectsVolume.ordinal() + 1) % Volume.values().length];
    }
}
