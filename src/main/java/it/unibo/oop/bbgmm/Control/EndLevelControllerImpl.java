package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.EndLevelView;
import it.unibo.oop.bbgmm.Boundary.ObservableView;
import it.unibo.oop.bbgmm.Entity.GameField;
import it.unibo.oop.bbgmm.Entity.GameStatistics;

/**
 * {@link EndLevelController} implementation.
 */
public class EndLevelControllerImpl implements EndLevelController {
    private static final int INCREMENT_LEVEL = 1;
    private final PrincipalController principalController;
    private final GameStatistics gameStatistics;
    private final Level level;

    EndLevelControllerImpl(final PrincipalController principalController, final GameStatistics gameStatistics,
                           final Level level, final ObservableView<EndLevelController> endLevelView) {
        this.principalController = principalController;
        this.gameStatistics = gameStatistics;
        this.level = level;
        endLevelView.setObserver(this);
    }

    @Override
    public void goToNextLevel() {
        this.gameStatistics.setCurrentLevel(gameStatistics.getCurrentLevel() + INCREMENT_LEVEL);
        this.level.initializeLevel();
        this.principalController.getGameController().get().start();
    }

    @Override
    public void goToMainMenu() {
        //this.principalController.showMainMenu();
        this.principalController.resetGame();
    }

    @Override
    public void goToEndLevel(final EndLevelView endLevelView) {
        this.principalController.showEndLevelView();
    }
}
