package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * @author Manuel
 * Class that creates the stage of the application
 */

public class MainWindow {
    private final Stage primaryStage;

    public MainWindow(final Stage primaryStage, final PrincipalController controller) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CROP MAZE");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setWidth(Resolution.getWidth());
        this.primaryStage.setHeight(Resolution.getHeight());
        this.primaryStage.centerOnScreen();
        this.primaryStage.setResizable(false);
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        this.primaryStage.setOnCloseRequest(e -> {
            this.primaryStage.close();
        });

        this.primaryStage.getIcons().add(new Image("images/mainMenu/icon.png"));
        //set the main menu as the scene
        ViewSwitcher.showMainMenu(this.primaryStage,controller);
        this.primaryStage.show();
    }
}
