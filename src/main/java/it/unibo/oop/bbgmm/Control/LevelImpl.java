package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.GameField;
import it.unibo.oop.bbgmm.Entity.Wall;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.TileLayer;

import java.util.Locale;
import java.util.Set;

/**
 * Controls a level.
 */
public class LevelImpl implements Level {

    private Entity player;
    private final Map map;
    private final GameField gameField;
    private final Set<Entity> entities;


    /**
     * Constructor for LevelImpl
     * @param map
     *          Map to load.
     * @param gameField
     *          GameField gameField
     * @param entities
     *          Entities in the level
     */
    public LevelImpl(final Map map, final GameField gameField, final Set<Entity> entities) {
        this.map = map;
        this.gameField = gameField;
        this.entities = entities;


        this.map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                loadTiles((TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                loadObjects((ObjectGroup) layer);
            }
        });
    }



    private void loadObjects(final ObjectGroup layer) {
        final ObjectGroup objLayer = layer;
        if (objLayer.getName().trim().toLowerCase(Locale.UK).equals("solid")) {
            objLayer.forEach(obj -> {
                final Pair<Point2D, Dimension2D> pos = mapPositionToWorld(this.map, obj.getX(), obj.getY(),
                        obj.getWidth(), obj.getHeight());


            });
        }
        if (layer.getName().trim().toLowerCase(Locale.UK).equals("objects")) {
            layer.forEach(mapObj -> {
                final String type = mapObj.getType();
                Entity entity;
                switch (type) {
                    case "player":

                        break;

                    //cases of all power ups and enemies

                    default:
                        break;
                }
            });
        }
    }

    private void loadTiles(final TileLayer layer) {

    }

    private Pair<Point2D, Dimension2D> mapPositionToWorld(final Map map,
                                                          final double x, final double y,
                                                          final double width, final double height) {
        final Dimension2D dim = new Dimension2D(width / map.getTileWidth(),
                height / map.getTileHeight());
        final Point2D pos = new Point2D(x / map.getTileWidth() + dim.getWidth() / 2,
                -(y / map.getTileHeight() + dim.getHeight() / 2));

        return new Pair<>(pos, dim);
    }


    @Override
    public Entity getPlayer() {
        return this.player;
    }

    @Override
    public GameField getGameField() {
        return this.gameField;
    }
}
