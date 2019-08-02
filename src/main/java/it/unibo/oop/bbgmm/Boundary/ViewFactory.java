package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Volume;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {

    private final Stage stage;
    private final Group root;
    private final Scene scene;
    private final PrincipalController controller;
    private AudioPlayer audioPlayer;

    public ViewFactory(Stage stage, PrincipalController controller, AudioPlayer audioPlayer, final Group root, final Scene scene) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        this.controller = controller;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Creates a new scene showing the MainMenu
     * @return MainMenu
     */
    public MainMenu createMainMenu(){
        return new MainMenu(stage,controller,audioPlayer, root, scene);
    }

    /**
     * Creates a new scene showing the RankingView
     * @return RankingView
     */
    public RankingView createRankingView(){
        return new RankingView(stage,controller,audioPlayer, root, scene);
    }

    /**
     * Creates a new scene showing the SettingsMenu
     * @return SettingsMenu
     */
    public SettingsMenu createSettingsMenu(){
        return new SettingsMenu(stage,controller,audioPlayer, root, scene);
    }

    /**
     * Creates a new scene showing the GameOver
     * @return GameOver
     */
    public GameOver createGameOver(){
        return new GameOver(stage,controller,audioPlayer, root, scene);
    }

    /**
     *  Creates a new scene showing the InsertScoreVIew
     * @return InsertScoreView
     */
    public InsertScoreView createInsertScoreView(){ return new InsertScoreView(stage,controller,audioPlayer, root, scene);
    }

    public void updateVolume(Volume musicVolume, Volume effectsVolume){
        this.audioPlayer = new AudioPlayerImpl(musicVolume.getValue(), effectsVolume.getValue());
    }
}
