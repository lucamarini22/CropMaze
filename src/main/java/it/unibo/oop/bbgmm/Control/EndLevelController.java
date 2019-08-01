package it.unibo.oop.bbgmm.Control;

/**
 * Controller that manages the end of a level.
 */
public interface EndLevelController {

    /**
     * Goes to the next {@link Level}.
     */
    void nextLevel();

    /**
     * Goes to the {@link it.unibo.oop.bbgmm.Boundary.MainMenu}.
     */
    void mainMenu();
}
