package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class MainMenu extends Scene {
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension d = tk.getScreenSize();
    private final double width;
    private final double height;
    private static Stage primaryStage;

    public MainMenu() {
        super(new StackPane());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.width = d.width/2;
        this.height = (2*d.height)/3;

    }

    public static MainMenu getMainMenu(Stage stage){
        primaryStage = stage;
        return new MainMenu();
    }

}
