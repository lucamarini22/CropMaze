package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.GameFieldView;

public interface GameController {
    /**
     * Method used to start the GameLoop
     */
    void run();

    /**
     * Method used to start the GameLoop
     */
    void start();

    /**
     * Method used to stop the GameLoop
     */
    void stop();

    /**
     * Getter fot the GameFieldView
     * @return GameFieldView
     */
    GameFieldView getGameFieldView();
}
