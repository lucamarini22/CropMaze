package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.EntityFactoryImpl;
import it.unibo.oop.bbgmm.Entity.GameField;
import it.unibo.oop.bbgmm.Entity.GameFieldImpl;
import it.unibo.oop.bbgmm.Entity.GameStatisticsImpl;
import javafx.animation.AnimationTimer;
import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;
import java.io.File;
import java.util.List;

public class GameControllerImpl implements GameController {

    private static final double FRAME = 1.0 / 60;

    private final GameField model;
    private final Level loadLevel;
    private Map map;
    private List<EntityController> entities;
   //il loop viene fatto da animation timer che esegue il metodo handle ogni tot secondi
    //loadLevel crea la mappa di gioco i personaggi e gli alieni
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update();
        }
    };

    public GameControllerImpl() {
        model = new GameFieldImpl();
        loadMap();
        loadLevel = new LevelImpl(this.map, this.model, new EntityFactoryImpl(), new GameStatisticsImpl());
        run();
    }

    private void loadMap() {
        File path = new File("");
        try {
            map = new TMXMapReader().readMap(path.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("ERROR: Can't load map\n");
            e.printStackTrace();
        }
    }

    private void run(){ timer.start(); }

    public void stop() { timer.stop(); }

    private void update() {
        //updates the view
        entities.forEach(EntityController::update);
        //updates the model
        model.update(FRAME);
    }
}
