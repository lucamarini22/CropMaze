package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.MENU_TRACK;

/**
 * Class that creates the window for the application.
 */
public class MainWindow {
    private final Stage primaryStage;
    private final AnchorPane root;
    private final Scene scene;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;
    private final PrincipalController controller;

    /**
     * Constructor for MainMenu.
     *
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param fullscreen
     *          Set the window in fullscreen
     */
    public MainWindow(final Stage primaryStage, final PrincipalController controller, final boolean fullscreen) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.audioPlayer = controller.getAudioPlayer();
        this.primaryStage.setTitle("CROP MAZE");
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
        this.root = new AnchorPane();
        this.scene = new Scene(root, Resolution.getWidth(), Resolution.getHeight());
        this.viewFactory = new ViewFactory(primaryStage, controller, this.root, this.scene);
        this.scene.getStylesheets().add("Style.css");
        this.primaryStage.setScene(scene);
        this.controller.showMainMenu(this.viewFactory);

        this.audioPlayer.playMusic(MENU_TRACK.getPath());

        this.primaryStage.setFullScreen(fullscreen);
        this.primaryStage.show();
    }

    /**
     * @return the view factory
     */
    public ViewFactory getViewFactory() {
        return this.viewFactory;
    }
}
