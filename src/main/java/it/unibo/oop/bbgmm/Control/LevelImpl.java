package it.unibo.oop.bbgmm.Control;

import com.badlogic.gdx.maps.tiled.TiledMap;
import it.unibo.oop.bbgmm.Entity.GameField;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.TileLayer;

import java.util.Locale;

public class LevelImpl implements Level {

    private Entity player;
    private final Map map;
    private final GameField gameField;

    /**
     * Constructor for LevelImpl
     * @param map
     *          Map to load.
     * @param gameField
     *          GameField gameField
     */
    public LevelImpl(final Map map, final GameField gameField) {
        this.map = map;
        this.gameField = gameField;


        this.map.forEach(layer -> {
            if (layer instanceof TileLayer) {

            } else if (layer instanceof ObjectGroup) {

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
        return null;
    }

    @Override
    public GameField getGameField() {
        return null;
    }
}
