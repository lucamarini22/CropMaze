package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.GameFieldView;
import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.entity.EntityFactory;
import it.unibo.oop.bbgmm.entity.EntityFactoryImpl;
import it.unibo.oop.bbgmm.entity.EntitySpawnerImpl;
import it.unibo.oop.bbgmm.entity.GameField;
import it.unibo.oop.bbgmm.entity.GameFieldImpl;
import it.unibo.oop.bbgmm.entity.GameStatistics;
import it.unibo.oop.bbgmm.entity.EntityStatisticsImpl;
import it.unibo.oop.bbgmm.entity.BasicScoreCalculator;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import org.mapeditor.core.Map;
import java.util.Set;

/**
 * {@link GameController} implementation.
 */
public final class GameControllerImpl implements GameController {

    private static final double FRAME = 1.0 / 60;
    private final GameField gameField;
    private Level level;
    private Set<EntityController> entitiesControllers;
    private GameFieldView gameFieldView;
    private final PrincipalController principalController;
    private final EndLevelController endLevelController;
    //the loop is made by animation timer which executes the handle method every few milliseconds
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            update();
        }
    };

    /**
     * {@link GameControllerImpl} constructor.
     *
     * @param gameStatistics
     *      Statistics of the game
     * @param gameFieldView
     *      View of the field
     * @param primaryStage
     *      The primary Stage
     * @param principalController
     *      {@link PrincipalController} instance
     */
    public GameControllerImpl(final GameStatistics gameStatistics, final GameFieldView gameFieldView, final Stage primaryStage,
                              final PrincipalController principalController) {
        this.gameFieldView = gameFieldView;
        this.gameField = new GameFieldImpl(new CollisionSupervisorImpl(), this);
        this.principalController = principalController;
        final EntityFactory entityFactory = new EntityFactoryImpl(this.gameField, new EntityStatisticsImpl(), gameStatistics);
        try {
            final Map map;
            map = new TMXMapLoader().loadMap();
            this.level = new LevelImpl(map,
                                       gameStatistics,
                                       new EntitySpawnerImpl(entityFactory, this.gameField),
                                       this.gameFieldView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.endLevelController = new EndLevelControllerImpl(this.principalController, gameStatistics, this.level, this.gameFieldView);
        new UpgradeControllerImpl(this.gameFieldView.getUpgradeButton(), this.level.getPlayer(), this, primaryStage);
    }

    @Override
    public void run() {
        this.gameFieldView = this.level.getGameFieldView();
        this.entitiesControllers = this.level.getEntitiesControllers();
        start();
    }

    @Override
    public void start() {
        this.timer.start();
    }

    @Override
    public void restart() {
        this.timer.start();
    }

    @Override
    public void stop() {
        this.timer.stop();
    }

    @Override
    public GameFieldView getGameFieldView() {
        return this.gameFieldView;
    }

    /**
     * Method called every loop to update the entities in the model and in the view.
     */
    private void update() {
        //updates the view
        this.entitiesControllers.forEach(EntityController::update);
        //updates the entity
        this.gameField.update(FRAME);
    }

    @Override
    public void triggerEndLevel() {
        this.endLevelController.goToEndLevel();
    }

    @Override
    public void triggerGameOver() {
        this.principalController.showGameOver(this.principalController.getView().getWindow().getViewFactory());
    }

    @Override
    public int calculateScore() {
        return new BasicScoreCalculator().getScore(this.gameField.getPlayerStatistic());
    }
}
