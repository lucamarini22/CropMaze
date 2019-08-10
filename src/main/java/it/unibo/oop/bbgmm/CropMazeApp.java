package it.unibo.oop.bbgmm;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Control.PrincipalControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher class.
 */
public class CropMazeApp extends Application {

    /**
     * Main.
     * @param args
     *      Arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final PrincipalController controller = new PrincipalControllerImpl(primaryStage);
    }
}
