package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static it.unibo.oop.bbgmm.Boundary.Music.BUTTON_SWITCH;

public abstract class AbstractBasicView extends Scene {

    private final PrincipalController controller;
    private final Stage primaryStage;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;

    public AbstractBasicView(final Stage primaryStage, final PrincipalController controller) {
        super(new AnchorPane(), Resolution.getWidth(), Resolution.getHeight());
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.viewFactory = new ViewFactory(primaryStage,controller);
        this.audioPlayer = new AudioPlayerImpl(30,10);
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
     * Getter for the Stage
     * @return primaryStage
     */
    protected Stage getPrimaryStage() {
        return primaryStage;
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
    protected void playSound(){
        this.audioPlayer.playSound(BUTTON_SWITCH.getPath());
    }
}
