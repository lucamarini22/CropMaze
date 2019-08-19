package it.unibo.oop.bbgmm.control;

/**
 * Controller that manages the end of a level.
 */
public interface EndLevelController {

    /**
     * Goes to the next {@link Level}.
     */
    void goToNextLevel();

    /**
     * Shows the end level view.
     */
    void goToEndLevel();
}
