package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import it.unibo.oop.bbgmm.Utilities.Volume;
import javafx.stage.Stage;

/**
 * Principal view which creates the window.
 */
public class PrincipalView {

    private final MainWindow window;

    /**
     * Constructor for PrincipalView.
     *
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     */
    public PrincipalView(final Stage primaryStage, PrincipalController controller) {
        this.window = new MainWindow(primaryStage, controller, Resolution.isFullScreen());
    }

    public MainWindow getWindow() {
        return this.window;
    }
}
