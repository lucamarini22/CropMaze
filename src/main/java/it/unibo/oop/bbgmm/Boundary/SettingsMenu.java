package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsMenu extends Scene {

    private static Stage primaryStage;
    private final AnchorPane pane;

    private VBox menuBox;
    private int currentItem = 0;
    private boolean isFullscreen = false;

    public SettingsMenu() {
        super(new AnchorPane(), Resolution.getWidth(), Resolution.getHeight());

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(currentItem).activate();
            }
        });

        pane = new AnchorPane();

        MenuItem itemExit = new MenuItem("BACK");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(25,
                new MenuItem("SMALL RESOLUTION"),
                new MenuItem("FULLSCREEN"),
                itemExit);

        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(365);
        menuBox.setTranslateY(350);

        getMenuItem(0).setActive(true);

        pane.getChildren().add(menuBox);

        pane.setId("mainMenu");

        this.setRoot(pane);

        this.getStylesheets().add("Style.css");
    }

    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    public static SettingsMenu getSettingsMenu(Stage stage) {
        primaryStage = stage;
        return new SettingsMenu();
    }
}