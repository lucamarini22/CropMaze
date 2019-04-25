package it.unibo.oop.bbgmm.Boundary;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class MainWindow extends Application {

    private final double width;
    private final double height;
    private final Stage primaryStage = new Stage();

    public MainWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.width = d.width/2;
        this.height = (2*d.height)/3;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage.setTitle("CROP MAZE");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setWidth(this.width);
        this.primaryStage.setHeight(this.height);
        this.primaryStage.centerOnScreen();

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
