package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import it.unibo.oop.bbgmm.utilities.Volume;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.boundary.Music.BUTTON_PRESS;
import static it.unibo.oop.bbgmm.boundary.Music.BUTTON_SWITCH;

/**
 * Model class for the view.
 */
public abstract class AbstractBasicView {

    private final PrincipalController controller;
    private final Stage primaryStage;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;
    private final AnchorPane root;
    private final Scene scene;

    /**
     * Constructor for AbstractBasicView.
     *
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param root
     *          The root for the scene
     * @param scene
     *          The scene displayed in the stage
     */
    public AbstractBasicView(final Stage primaryStage, final PrincipalController controller, final AnchorPane root, final Scene scene) {
        this.root = root;
        this.scene = scene;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.audioPlayer = controller.getAudioPlayer();
        this.viewFactory = new ViewFactory(primaryStage, controller, root, scene);
    }

    /**
     * Method used to set the action for each item.
     */
    protected abstract void itemActions();

    /**
     * Getter for the controller.
     *
     * @return controller
     *          The principal controller
     */
    protected PrincipalController getController() {
        return this.controller;
    }

    /**
     * Getter for the Root.
     *
     * @return root
     *          The root of the scene
     */
    protected AnchorPane getRoot() {
        return this.root;
    }

    /**
     * Getter for the Scene.
     *
     * @return scene
     *          The scene displayed in the stage
     */
    protected Scene getScene() {
        return this.scene;
    }


    /**
     * Getter for the viewFactory.
     *
     * @return viewFactory
     *          The factory for the views
     */
    protected ViewFactory getViewFactory() {
        return viewFactory;
    }

    /**
     * Getter for the AudioPlayer.
     *
     * @return audioPlayer
     *          The audioPlayer
     */
    protected AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    /**
     * Method used to set or not the stage to FullScreen.
     */
    protected void checkResolution() {
        if (ResolutionUtil.isFullScreen()) {
            this.primaryStage.setFullScreen(true);
        } else {
            this.primaryStage.setFullScreen(false);
            this.primaryStage.centerOnScreen();
        }
    }

    /**
     * Set the volume for the music and fot the effects.
     *
     * @param musicVolume
     *          The volume of the music
     * @param effectsVolume
     *          The volume of the effects
     */
    protected void updateVolume(final Volume musicVolume, final Volume effectsVolume) {
        this.audioPlayer.setMusicVolume(musicVolume.getValue());
        this.audioPlayer.setSoundVolume(effectsVolume.getValue());
        getController().updateVolume(musicVolume, effectsVolume);
    }

    /**
     * Method called to play the buttonSwitch sound.
     */
    protected void playSwitchSound() {
        this.audioPlayer.playSound(BUTTON_SWITCH.getPath());
    }

    /**
     * Method called to play the buttonPress sound.
     */
    protected void playPressSound() {
        this.audioPlayer.playSound(BUTTON_PRESS.getPath());
    }
}
