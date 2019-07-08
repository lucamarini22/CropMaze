package it.unibo.oop.bbgmm.Control;

import com.badlogic.gdx.maps.Map;
import it.unibo.oop.bbgmm.Entity.GameField;

public class LevelImpl implements Level {

    private Entity player;
    private final Map map;
    private final GameField gameField;

    LevelImpl() {


    }

    @Override
    public Entity getPlayer() {
        return null;
    }

    @Override
    public GameField getGameField() {
        return null;
    }
}
