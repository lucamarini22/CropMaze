package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import static it.unibo.oop.bbgmm.Boundary.Music.MENU_TRACK;

/**
 * @author Manuel
 * Class that creates the stage of the application
 */

public class MainWindow {
    private final static double SOUND_VOLUME = 1;
    private final static double MUSIC_VOLUME = 0.4;
    private final Stage primaryStage;
    private final Group root;
    private final Scene scene;
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;
    private final PrincipalController controller;

    public MainWindow(final Stage primaryStage, final PrincipalController controller) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.audioPlayer = new AudioPlayerImpl(SOUND_VOLUME,MUSIC_VOLUME);
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
        this.root = new Group();
        this.scene = new Scene(root, Resolution.getWidth(), Resolution.getHeight());
        this.viewFactory = new ViewFactory(primaryStage, controller, audioPlayer, root, scene);
        this.primaryStage.setScene(scene);
        this.controller.showMainMenu(this.viewFactory);

        this.audioPlayer.playMusic(MENU_TRACK.getPath());
        this.primaryStage.show();
    }

    private void setPlayerInputHandler(){
        this.controller.setPlayerInputHandler(new PlayerInputHandler(this.scene));
    }
}
