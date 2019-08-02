package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_PRESS;
import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_SWITCH;

public abstract class AbstractBasicView {

    private final PrincipalController controller;
    private final Stage primaryStage;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;
    private final Group root;
    private final Scene scene;

    public AbstractBasicView(final Stage primaryStage, final PrincipalController controller, final AudioPlayer audioPlayer, final Group root, final Scene scene) {
        this.root = root;
        this.scene = scene;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.audioPlayer = audioPlayer;
        this.viewFactory = new ViewFactory(primaryStage, controller, audioPlayer, root, scene);
    }

    /**
     * Method used to set the action for each button.
     */
    protected abstract void buttonActions();

    /**
     * Getter for the controller
     * @return controller
     */
    protected PrincipalController getController() { return this.controller; }

    /**
     * Getter for the Root
     * @return root
     */
    protected Group getRoot() {
        return this.root;
    }

    /**
     * Getter for the Scene
     * @return scene
     */
    protected Scene getScene() {
        return this.scene;
    }

    /**
     * Getter for the Stage
     * @return primaryStage
     */
    protected Stage getPrimaryStage() {
        return this.primaryStage;
    }

    /**
     * Getter for the viewFactory
     * @return viewFactory
     */
    protected ViewFactory getViewFactory() {
        return viewFactory;
    }

    /**
     * Getter for the AudioPlayer
     * @return audioPlayer
     */
    protected AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    /**
     * Method used to set or not the stage to FullScreen
     */
    protected void checkResolution(){
        if(Resolution.isFullScreen()){
            primaryStage.setFullScreen(true);
        }
        else{
            primaryStage.setFullScreen(false);
            primaryStage.centerOnScreen();
        }
    }

    /**
     * Method called to play the buttonSwitch sound
     */
    protected void playSwitchSound(){
        this.audioPlayer.playSound(BUTTON_SWITCH.getPath());
    }

    /**
     * Method called to play the buttonSwitch sound
     */
    protected void playPressSound(){
        this.audioPlayer.playSound(BUTTON_PRESS.getPath());
    }
}
