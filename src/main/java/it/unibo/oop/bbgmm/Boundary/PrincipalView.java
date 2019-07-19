package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import javafx.stage.Stage;

public class PrincipalView {

    private final MainWindow window;

    public PrincipalView(final Stage primaryStage, PrincipalController controller) {
        this.window = new MainWindow(primaryStage, controller);
    }
}
