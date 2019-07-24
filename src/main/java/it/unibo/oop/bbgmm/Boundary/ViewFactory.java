package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import javafx.stage.Stage;

public class ViewFactory {

    private final Stage stage;
    private final PrincipalController controller;

    public ViewFactory(Stage stage, PrincipalController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public MainMenu createMainMenu(){
        return new MainMenu(stage,controller);
    }

    public RankingView createRankingView(){
        return new RankingView(stage,controller);
    }

    public SettingsMenu createSettingsMenu(){
        return new SettingsMenu(stage,controller);
    }

    public GameOver createGameOver(){
        return new GameOver(stage,controller);
    }
}
