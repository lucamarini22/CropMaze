package it.unibo.oop.bbgmm.control;

import com.google.common.io.Files;
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
import it.unibo.oop.bbgmm.utilities.ZipExtractorUtil;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import java.io.File;
import java.io.InputStream;
import java.util.Set;

/**
 * {@link GameController} implementation.
 */
public final class GameControllerImpl implements GameController {

    private static final double FRAME = 1.0 / 60;
    private static final String MAP_PATH = "/images/Map/CropMazeMap.zip";
    private static final String MAP_NAME = "CropMazeMap.tmx";
    private final GameField gameField;
    private final Level level;
    private Map map;
    private Set<EntityController> entitiesControllers;
    private final EntityFactory entityFactory;
    private GameFieldView gameFieldView;
    private final GameStatistics gameStatistics;
    private final UpgradeController upgradeController;
    //the loop is made by animation timer which executes the handle method every few seconds
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            update();
        }
    };
    private final PrincipalController principalController;
    private final EndLevelController endLevelController;

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
        this.gameStatistics = gameStatistics;
        this.entityFactory = new EntityFactoryImpl(this.gameField, new EntityStatisticsImpl(), gameStatistics);
        try {
            loadMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.level = new LevelImpl(this.map,
                                   this.gameStatistics,
                                   new EntitySpawnerImpl(this.entityFactory, this.gameField),
                                   this.gameFieldView);
        this.endLevelController = new EndLevelControllerImpl(this.principalController, this.gameStatistics, this.level, this.gameFieldView);
        this.upgradeController = new UpgradeControllerImpl(this.gameFieldView.getUpgradeButton(), this.level.getPlayer(), this, primaryStage);
    }

    /**
     * Method called to load the Map.
     *
     * @throws Exception
     *          Throw exception if the file does not exist
     */
    private void loadMap() throws Exception {
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream(MAP_PATH)) {
            ZipExtractorUtil.extract(is, tempDir);
            this.map = new TMXMapReader().readMap(new File(tempDir, MAP_NAME).getAbsolutePath());
        } catch (final Exception e) {
            System.out.println("ERROR: Can't load map\n");
        }
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
