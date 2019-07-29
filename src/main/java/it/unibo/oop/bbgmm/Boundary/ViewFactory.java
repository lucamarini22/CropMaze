package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import javafx.stage.Stage;

public class ViewFactory {

    private final Stage stage;
    private final PrincipalController controller;
    private final AudioPlayer audioPlayer;

    public ViewFactory(Stage stage, PrincipalController controller, AudioPlayer audioPlayer) {
        this.stage = stage;
        this.controller = controller;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Creates a new scene showing the MainMenu
     * @return MainMenu
     */
    public MainMenu createMainMenu(){
        return new MainMenu(stage,controller,audioPlayer);
    }

    /**
     * Creates a new scene showing the RankingView
     * @return RankingView
     */
    public RankingView createRankingView(){
        return new RankingView(stage,controller,audioPlayer);
    }

    /**
     * Creates a new scene showing the SettingsMenu
     * @return SettingsMenu
     */
    public SettingsMenu createSettingsMenu(){
        return new SettingsMenu(stage,controller,audioPlayer);
    }

    /**
     * Creates a new scene showing the GameOver
     * @return GameOver
     */
    public GameOver createGameOver(){
        return new GameOver(stage,controller,audioPlayer);
    }
}
