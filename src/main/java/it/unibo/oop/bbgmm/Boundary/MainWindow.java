package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
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
    private final ViewFactory viewFactory;
    private final AudioPlayer audioPlayer;

    public MainWindow(final Stage primaryStage, final PrincipalController controller) {
        this.primaryStage = primaryStage;
        this.audioPlayer = new AudioPlayerImpl(SOUND_VOLUME,MUSIC_VOLUME);
        this.viewFactory = new ViewFactory(primaryStage, controller, audioPlayer);
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
        this.primaryStage.setScene(this.viewFactory.createMainMenu());
        this.audioPlayer.playMusic(MENU_TRACK.getPath());
        this.primaryStage.show();
    }
}
