package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import it.unibo.oop.bbgmm.Utilities.Volume;
import javafx.stage.Stage;

public class PrincipalView {

    private final MainWindow window;

    public PrincipalView(final Stage primaryStage, PrincipalController controller) {
        this.window = new MainWindow(primaryStage, controller, Resolution.isFullScreen());
    }

    public MainWindow getWindow() {
        return this.window;
    }
}
