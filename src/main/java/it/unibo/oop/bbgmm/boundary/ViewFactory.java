package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Factory for the views.
 */
public class ViewFactory {

    private final Stage stage;
    private final AnchorPane root;
    private final Scene scene;
    private final PrincipalController controller;

    /**
     * Constructor for ViewFactory.
     *
     * @param stage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param root
     *          The root for the scene
     * @param scene
     *          The scene displayed in the stage
     */
    public ViewFactory(final Stage stage, final PrincipalController controller,
                       final AnchorPane root, final Scene scene) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        this.controller = controller;
    }

    /**
     * Creates a new scene showing the MainMenu.
     *
     * @return MainMenu
     *          The view for MainMenu
     */
    public MainMenu createMainMenu() {
        return new MainMenu(this.stage, this.controller, this.root, this.scene);
    }

    /**
     * Creates a new scene showing the RankingView.
     *
     * @return RankingView
     *          The view for RankingView
     */
    public RankingView createRankingView() {
        return new RankingView(this.stage, this.controller, this.root, this.scene);
    }

    /**
     * Creates a new scene showing the SettingsMenu.
     *
     * @return SettingsMenu
     *          The view for SettingsMenu
     */
    public SettingsMenu createSettingsMenu() {
        return new SettingsMenu(this.stage, this.controller, this.root, this.scene);
    }

    /**
     * Creates a new scene showing the GameOver.
     *
     * @return GameOver
     *          The view for GameOver
     */
    public GameOver createGameOver() {
        return new GameOver(this.stage, this.controller, this.root, this.scene);
    }
}
