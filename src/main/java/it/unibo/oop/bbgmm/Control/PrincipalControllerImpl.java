package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.MainWindow;
import it.unibo.oop.bbgmm.Boundary.PrincipalView;
import javafx.stage.Stage;

public class PrincipalControllerImpl implements PrincipalController {

    private final PrincipalView view;

    public PrincipalControllerImpl(final Stage principalStage) {
        this.view = new PrincipalView(principalStage, this);
    }
}
