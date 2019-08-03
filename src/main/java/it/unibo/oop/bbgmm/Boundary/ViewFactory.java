package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Volume;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private final Stage stage;
    private final AnchorPane root;
    private final Scene scene;
    private final PrincipalController controller;

    public ViewFactory(Stage stage, PrincipalController controller, final AnchorPane root, final Scene scene) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        this.controller = controller;
    }

    /**
     * Creates a new scene showing the MainMenu
     * @return MainMenu
     */
    public MainMenu createMainMenu(){
        return new MainMenu(stage,controller, root, scene);
    }

    /**
     * Creates a new scene showing the RankingView
     * @return RankingView
     */
    public RankingView createRankingView(){
        return new RankingView(stage,controller, root, scene);
    }

    /**
     * Creates a new scene showing the SettingsMenu
     * @return SettingsMenu
     */
    public SettingsMenu createSettingsMenu(){
        return new SettingsMenu(stage,controller, root, scene);
    }

    /**
     * Creates a new scene showing the GameOver
     * @return GameOver
     */
    public GameOver createGameOver(){
        return new GameOver(stage,controller, root, scene);
    }

    /**
     *  Creates a new scene showing the InsertScoreVIew
     * @return InsertScoreView
     */
    public InsertScoreView createInsertScoreView(){ return new InsertScoreView(stage,controller, root, scene);
    }
}
