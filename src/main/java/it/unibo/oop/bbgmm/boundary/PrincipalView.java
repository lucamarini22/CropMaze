package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
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
        this.window = new MainWindow(primaryStage, controller, ResolutionUtil.isFullScreen());
    }

    /**
     * @return the {@link MainWindow}
     */
    public MainWindow getWindow() {
        return this.window;
    }
}
