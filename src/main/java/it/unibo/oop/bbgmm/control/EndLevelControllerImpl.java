package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.GameFieldView;
import it.unibo.oop.bbgmm.entity.GameStatistics;

/**
 * {@link EndLevelController} implementation.
 */
public final class EndLevelControllerImpl implements EndLevelController {
    private static final int INCREMENT_LEVEL = 1;
    private final PrincipalController principalController;
    private final GameStatistics gameStatistics;
    private final Level level;
    private final GameFieldView gameFieldView;

    EndLevelControllerImpl(final PrincipalController principalController, final GameStatistics gameStatistics,
                           final Level level, final GameFieldView gameFieldView) {
        this.principalController = principalController;
        this.gameStatistics = gameStatistics;
        this.level = level;
        this.gameFieldView = gameFieldView;
        this.gameFieldView.setObserver(this);
    }

    @Override
    public void goToNextLevel() {
        this.gameStatistics.setCurrentLevel(gameStatistics.getCurrentLevel() + INCREMENT_LEVEL);
        this.level.initializeLevel();
        this.principalController.getGameController().get().start();
    }

    @Override
    public void goToEndLevel() {
        this.gameFieldView.showEndLevelBox(this.principalController);
    }
}
