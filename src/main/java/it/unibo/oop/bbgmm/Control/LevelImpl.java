package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
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
    private final EntityFactory entityFactory;
    //private final EntitySpawner spawner;






    /**
     * Constructor for LevelImpl
     * @param map
     *          Map to load.
     * @param gameField
     *          GameField gameField
     * @param entities
     *          Entities in the level
     */
    public LevelImpl(final Map map, final GameField gameField, final Set<Entity> entities, final EntityFactory entityFactory) {
        this.map = map;
        this.gameField = gameField;
        this.entities = entities;
        this.entityFactory = entityFactory;

        this.map.forEach(layer -> {
            if (layer instanceof TileLayer) {
                loadTiles((TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                loadObjects((ObjectGroup) layer);
            }
        });
    }

    @Override
    public Entity getPlayer() {
        return this.player;
    }

    @Override
    public GameField getGameField() {
        return this.gameField;
    }

    private static Point2D invertY(final Point2D pt) {
        return new Point2D(pt.getX(), -pt.getY());
    }


    private void loadObjects(final ObjectGroup layer) {
        final ObjectGroup objLayer = layer;
        if (objLayer.getName().trim().toLowerCase(Locale.UK).equals("solid")) {
            objLayer.forEach(obj -> {
                final Pair<Point2D, Dimension2D> pos = mapPositionToWorld(this.map, obj.getX(), obj.getY(),
                        obj.getWidth(), obj.getHeight());
                gameField.addEntity(entityFactory.createWall(pos.getKey(), pos.getValue()));

            });
        }
        if (layer.getName().trim().toLowerCase(Locale.UK).equals("objects")) {
            layer.forEach(mapObj -> {
                final Point2D position = invertY(new Point2D(mapObj.getX() / 70, mapObj.getY() / 70));
                final String type = mapObj.getType();
                Entity entity;
                switch (type) {
                    //creation of the player
                    case "player":
                        player = gameField.addEntity(entityFactory.createPlayer(position));
                        break;

                    //creation of all power ups, coins and enemies
                    case "coin":
                        entity = gameField.addEntity(entityFactory.createCoin(position));
                        break;

                    case "alien":
                        entity = gameField.addEntity(entityFactory.createEnemy(position));
                        break;
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



}
