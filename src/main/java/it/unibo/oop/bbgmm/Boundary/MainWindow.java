package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainWindow extends Application {
    private final Stage primaryStage = new Stage();

    public MainWindow() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage.setTitle("CROP MAZE");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setWidth(Resolution.getWidth());
        this.primaryStage.setHeight(Resolution.getHeight());
        this.primaryStage.centerOnScreen();
        this.primaryStage.setResizable(false);

        this.primaryStage.setOnCloseRequest(e -> {
            this.primaryStage.close();
        });
        //set as the scene the main menu
        this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage));
        this.primaryStage.show();

        //manca metodo per mettere suono
    }

    public static void main(String[] args) {
        launch(args);
    }
}
