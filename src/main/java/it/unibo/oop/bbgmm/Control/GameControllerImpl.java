package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.*;
import javafx.animation.AnimationTimer;
import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;
import java.io.File;
import java.util.List;

public class GameControllerImpl implements GameController {

    private static final double FRAME = 1.0 / 60;
    private static final String MAP_PATH = "images/map/CropMazeMap.tmx";

    private final GameField gameField;
    private final Level level;
    private Map map;
    private List<EntityController> entities;
    private final EntitySpawner entitySpawner;
    private final EntityFactory entityFactory;
    //il loop viene fatto da animation timer che esegue il metodo handle ogni tot secondi
    //level crea la mappa di gioco i personaggi e gli alieni
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update();
        }
    };

    public GameControllerImpl() {
        gameField = new GameFieldImpl();
        try {
            loadMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameStatistics gameStatistics = new GameStatisticsImpl();
        this.entityFactory = new EntityFactoryImpl(this.gameField.getWalls(), new EntityStatisticsImpl(), gameStatistics);
        this.entitySpawner = new EntitySpawnerImpl(this.entityFactory, gameField);
        level = new LevelImpl(this.map, this.gameField, gameStatistics, this.entitySpawner);
        run();
    }
    /**
     * Method called to load the Map.
     */
    private void loadMap() throws Exception {
        File path = new File(MAP_PATH);
        try {
            this.map = new TMXMapReader().readMap(path.getAbsolutePath());
        } catch (final Exception e) {
            throw new Exception("ERROR: Can't load map\n");
        }
    }

    /**
     * Method called to start the Timer
     */
    private void run(){ timer.start(); }

    @Override
    public void stop() { timer.stop(); }

    /**
     * Method called every loop to aupdate the entities in the model and in the view
     */
    private void update() {
        //updates the view
        entities.forEach(EntityController::update);
        //updates the model
        gameField.update(FRAME);
    }
}
