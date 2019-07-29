package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.BUTTONSWITCH;

public abstract class AbstractBasicView extends Scene {

    private final PrincipalController controller;
    private final Stage primaryStage;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer = new AudioPlayerImpl(10,10);

    public AbstractBasicView(final Stage primaryStage, final PrincipalController controller) {
        super(new AnchorPane(), Resolution.getWidth(), Resolution.getHeight());
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.viewFactory = new ViewFactory(primaryStage,controller);
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

    protected void playSound(){
        this.audioPlayer.playSound(BUTTONSWITCH.getPath());
    }
}
