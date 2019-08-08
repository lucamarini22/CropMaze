package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.GameFieldView;

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
     * Getter fot the GameFieldView
     * @return GameFieldView
     *          The view of the Gamefield
     */
    GameFieldView getGameFieldView();

    public void triggerEndLevel();
}
