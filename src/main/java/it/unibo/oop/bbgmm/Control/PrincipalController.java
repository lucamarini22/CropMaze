package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.PlayerInputHandler;
import it.unibo.oop.bbgmm.Boundary.ViewFactory;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.util.List;

/**
 * Main controller of the game
 */
public interface PrincipalController {

    /**
     * Getter for the ranking list
     * @return List<Pair<String, Integer>>
     */
    List<Pair<String, Integer>> getRankingList();

    /**
     * Method used to insert in the ranking a new score
     * @param name
     * @param result
     */
    void InsertNewScore(final String name, final Integer result);

    /**
     * Method that stops the game
     */
    void stopGame();

    /**
     * Method that starts the game
     */
    void startGame();

    /**
     * Method used by the view to show the main menu
     * @param viewFactory
     */
    void showMainMenu(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the ranking
     * @param viewFactory
     */
    void showRankingView(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the settings
     * @param viewFactory
     */
    void showSettings(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the GameField and start the game
     * @param group
     */
    void showGameField(final Group group);

    /**
     * Method used by the view to show the insertScore screen
     * @param viewFactory
     */
    void showInsertScoreView(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the gameOver screen
     * @param viewFactory
     */
    void showGameOver(final ViewFactory viewFactory);

    /**
     * Method invoked by the view to set the PlayerInputListener
     * @param playerInputHandler
     */
    void setPlayerInputHandler(final PlayerInputHandler playerInputHandler);
}
