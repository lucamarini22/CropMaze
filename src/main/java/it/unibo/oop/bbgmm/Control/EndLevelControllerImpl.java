package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.GameStatistics;

/**
 * {@link EndLevelController} implementation.
 */
public class EndLevelControllerImpl implements EndLevelController {
    private static final int INCREMENT_LEVEL = 1;
    private final PrincipalController principalController;

    EndLevelControllerImpl(final PrincipalController principalController, final GameStatistics gameStatistics) {
        gameStatistics.setCurrentLevel(gameStatistics.getCurrentLevel() + INCREMENT_LEVEL);
        this.principalController = principalController;
    }

    @Override
    public void nextLevel() {
        //this.principalController...
    }

    @Override
    public void mainMenu() {
        //this.principalController...
    }
}
