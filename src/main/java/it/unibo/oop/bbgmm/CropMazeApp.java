package it.unibo.oop.bbgmm;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Control.PrincipalControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class CropMazeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        PrincipalController controller = new PrincipalControllerImpl(primaryStage);
    }
}
