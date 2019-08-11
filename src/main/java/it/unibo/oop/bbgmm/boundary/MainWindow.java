package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.boundary.Music.MENU_TRACK;

/**
 * Class that creates the window for the application.
 */
public class MainWindow {
    private final ViewFactory viewFactory;

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
        primaryStage.setTitle("CROP MAZE");
        primaryStage.setWidth(ResolutionUtil.getWidth());
        primaryStage.setHeight(ResolutionUtil.getHeight());
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
        });

        primaryStage.getIcons().add(new Image("images/mainMenu/icon.png"));
        //set the main menu as the scene
        final AnchorPane root = new AnchorPane();
        final Scene scene = new Scene(root, ResolutionUtil.getWidth(), ResolutionUtil.getHeight());
        this.viewFactory = new ViewFactory(primaryStage, controller, root, scene);
        scene.getStylesheets().add("Style.css");
        primaryStage.setScene(scene);
        controller.showMainMenu(this.viewFactory);

        controller.getAudioPlayer().playMusic(MENU_TRACK.getPath());

        primaryStage.setFullScreen(fullscreen);
        primaryStage.show();
    }

    /**
     * @return the view factory
     */
    public ViewFactory getViewFactory() {
        return this.viewFactory;
    }
}
