package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Manuel
 */

public class MainWindow extends Application {
    private final Stage primaryStage = new Stage();

    public MainWindow() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage.setTitle("CROP MAZE");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setWidth(Resolution.getSmallWidth());
        this.primaryStage.setHeight(Resolution.getSmallHeight());
        this.primaryStage.centerOnScreen();
        this.primaryStage.setResizable(false);

        this.primaryStage.setOnCloseRequest(e -> {
            this.primaryStage.close();
        });

        //metodo per l'icona
        //this.primaryStage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("icon.png")));
        //set as the scene the main menu
        this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage));
        this.primaryStage.show();

        //manca metodo per mettere suono
    }

    public static void main(String[] args) {
        launch(args);
    }
}
