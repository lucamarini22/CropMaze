package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.EndLevelView;

/**
 * Controller that manages the end of a level.
 */
public interface EndLevelController {

    /**
     * Goes to the next {@link Level}.
     */
    void goToNextLevel();

    /**
     * Goes to the {@link it.unibo.oop.bbgmm.Boundary.MainMenu}.
     */
    void goToMainMenu();


    void goToEndLevel(EndLevelView endLevelView);
}
