package it.unibo.oop.bbgmm.Control;

import com.google.common.io.Files;
import it.unibo.oop.bbgmm.Boundary.EndLevelView;
import it.unibo.oop.bbgmm.Boundary.GameFieldView;
import it.unibo.oop.bbgmm.Boundary.ViewFactory;
import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.Utilities.ZipExtractor;
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
    private GameField gameField;
    private Level level;
    private Map map;
    private Set<EntityController> entitiesControllers;
    private final EntitySpawner entitySpawner;
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
    private final EndLevelView endLevelView;

    /**
     * {@link GameControllerImpl} constructor.
     * @param gameStatistics
     *      Statistics of the game
     * @param gameFieldView
     *      View of the field
     * @param primaryStage
     *      {@link PrincipalController} instance
     */
    public GameControllerImpl(final GameStatistics gameStatistics, final GameFieldView gameFieldView, final Stage primaryStage,
                              final PrincipalController principalController) {
        this.gameFieldView = gameFieldView;
        this.gameField = new GameFieldImpl(new CollisionSupervisorImpl(), this);
        this.principalController = principalController;
        this.gameStatistics = gameStatistics;
        this.entityFactory = new EntityFactoryImpl(this.gameField, new EntityStatisticsImpl(), gameStatistics);
        this.entitySpawner = new EntitySpawnerImpl(this.entityFactory, gameField);
        try {
            loadMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.level = new LevelImpl(this.map, this.gameStatistics, this.entitySpawner, this.gameFieldView);
        this.endLevelView = this.principalController.showEndLevelView();
        this.endLevelController = new EndLevelControllerImpl(this.principalController, this.gameStatistics, this.level,
                this.endLevelView);

        //this.gameField.setLevel(level);
        this.upgradeController = new UpgradeControllerImpl(this.gameFieldView.getUpgradeButton(), this.level.getPlayer(), this, primaryStage);
    }

    /**
     * Method called to load the Map.
     */
    private void loadMap() throws Exception {
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream(MAP_PATH)) {
            ZipExtractor.extract(is, tempDir);
            this.map = new TMXMapReader().readMap(new File(tempDir, MAP_NAME).getAbsolutePath());
        } catch (final Exception e) {
            throw new Exception("ERROR: Can't load map\n");
        }
    }

    @Override
    public void run() {
        this.gameFieldView = level.getGameFieldView();
        this.entitiesControllers = level.getEntitiesControllers();
        start();
    }

    @Override
    public void start(){
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
     * Method called every loop to apdate the entities in the model and in the view.
     */
    private void update() {
        //updates the view
        this.entitiesControllers.forEach(EntityController::update);
        //updates the model
        this.gameField.update(FRAME);
    }

    @Override
    public void triggerEndLevel() {
        this.endLevelController.goToEndLevel(endLevelView);
    }
}
