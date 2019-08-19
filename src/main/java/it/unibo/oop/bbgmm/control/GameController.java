package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.GameFieldView;

/**
 * The controller of the game.
 */
public interface GameController {

    /**
     * Method used to start the GameLoop.
     */
    void run();

    /**
     * Method used to start the GameLoop.
     */
    void start();

    /**
     * Method used to stop the GameLoop.
     */
    void stop();

    /**
     * Method used to restart the GameLoop.
     */
    void restart();

    /**
     * Getter fot the GameFieldView.
     *
     * @return GameFieldView
     *          The view of the Gamefield
     */
    GameFieldView getGameFieldView();

    /**
     * Method that calculates the score of the player.
     *
     * @return int
     *          The score achieved
     */
    int calculateScore();

    /**
     * Triggers end level.
     */
    void triggerEndLevel();

    /**
     * Triggers game over.
     */
    void triggerGameOver();
}
