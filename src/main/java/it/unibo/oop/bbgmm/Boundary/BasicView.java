package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class BasicView extends Scene {

    private final PrincipalController controller;
    protected final Stage primaryStage;
    protected final ViewFactory viewFactory;

    public BasicView(final Stage primaryStage, final PrincipalController controller) {
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
}
