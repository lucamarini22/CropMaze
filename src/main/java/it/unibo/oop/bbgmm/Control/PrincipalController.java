package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.ViewFactory;
import it.unibo.oop.bbgmm.Utilities.Pair;
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
     * @param stage
     * @param viewFactory
     */
    void showMainMenu(final Stage stage, final ViewFactory viewFactory);

    /**
     * Method used by the view to show the GameField and start the game
     * @param stage
     */
    void showGameField(final Stage stage);

    /**
     * Method used by the view to show the gameOver screen
     * @param stage
     * @param viewFactory
     */
    void showGameOver(final Stage stage, final ViewFactory viewFactory);
}
