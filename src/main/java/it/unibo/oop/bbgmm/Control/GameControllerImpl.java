package it.unibo.oop.bbgmm.Control;

import com.google.common.io.Files;
import it.unibo.oop.bbgmm.Boundary.GameFieldView;
import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.Utilities.ZipExtractor;
import javafx.animation.AnimationTimer;
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

    private final PrincipalController principalController;
    private GameField gameField;
    private Level level;
    private Map map;
    private Set<EntityController> entitiesControllers;
    private final EntitySpawner entitySpawner;
    private final EntityFactory entityFactory;
    private GameFieldView gameFieldView;
    private final GameStatistics gameStatistics;
    private final UpgradeController upgradeController;
    //il loop viene fatto da animation timer che esegue il metodo handle ogni tot secondi
    //level crea la mappa di gioco i personaggi e gli alieni
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(final long now) {
            update();
        }
    };

    /**
     * {@link GameControllerImpl} constructor.
     * @param gameStatistics
     *      Statistics of the game
     * @param gameFieldView
     *      View of the field
     * @param principalController
     *      {@link PrincipalController} instance
     */
    public GameControllerImpl(final GameStatistics gameStatistics, final GameFieldView gameFieldView, final PrincipalController principalController) {
        gameField = new GameFieldImpl(new CollisionSupervisorImpl());
        this.principalController = principalController;
        this.gameStatistics = gameStatistics;
        this.gameFieldView = gameFieldView;
        try {
            loadMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.entityFactory = new EntityFactoryImpl(this.gameField, new EntityStatisticsImpl(), gameStatistics);
        this.entitySpawner = new EntitySpawnerImpl(this.entityFactory, gameField);
        level = new LevelImpl(this.map, this.gameField, this.gameStatistics, this.entitySpawner, this.gameFieldView);
        this.gameField.setLevel(level);
        this.upgradeController = new UpgradeControllerImpl(this.gameFieldView.getUpgradeButton(), this.level.getPlayer(), this);
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

    /**
     * Method called to start the Timer.
     */
    public void run() {
        this.gameField = level.getGameField();
        this.gameFieldView = level.getGameFieldView();
        this.entitiesControllers = level.getEntitiesControllers();
        timer.start();
    }

    @Override
    public void restart() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public GameFieldView getGameFieldView() {
        return this.gameFieldView;
    }

    /**
     * Method called every loop to aupdate the entities in the model and in the view.
     */
    private void update() {
        //updates the view
        entitiesControllers.forEach(EntityController::update);
        //updates the model
        gameField.update(FRAME);
    }
}
