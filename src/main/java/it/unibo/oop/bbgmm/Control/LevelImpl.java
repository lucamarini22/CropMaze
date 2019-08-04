package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.GameFieldView;
import it.unibo.oop.bbgmm.Entity.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.TileLayer;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Controls a level.
 */
public final class LevelImpl implements Level {
    private static final float TILE_SIZE = 1f;
    private static final int FIRST_LEVEL = 1;
    private static final int TOP_LEFT_X = 0;
    private static final int TOP_LEFT_Y = 0;
    private static final int POSITION_DIVISOR_SPAWN_X = 32;
    private static final int POSITION_DIVISOR_SPAWN_Y = 32;
    private static final String SOLID_OBJECTS = "solid";
    private static final String ENTITY_OBJECTS = "objects";

    private Entity player;
    private final Map map;
    private final GameField gameField;
    private final EntitySpawner entitySpawner;
    private PlayerStatistics playerStatistics;
    private final GameFieldView gameFieldView;
    private final Set<EntityController> entitiesControllers;
    private final GameStatistics gameStatistics;

    /**
     * Constructor for LevelImpl.
     *
     * @param map
     *          Map to load
     * @param gameField
     *          GameField gameField
     * @param gameStatistics
     *          Statistics of the game
     * @param entitySpawner
     *          {@link EntitySpawner} that spawns entities
     * @param gameFieldView
     *          View of the {@link GameField}
     */
    public LevelImpl(final Map map, final GameField gameField, final GameStatistics gameStatistics,
                     final EntitySpawner entitySpawner, final GameFieldView gameFieldView) {
        this.map = map;
        this.gameField = gameField;
        this.gameStatistics = gameStatistics;
        this.entitySpawner = entitySpawner;
        this.gameFieldView = gameFieldView;
        this.entitiesControllers = new LinkedHashSet<>();

        this.map.getLayers().forEach(layer -> {

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

    @Override
    public GameFieldView getGameFieldView() {
        return this.gameFieldView;
    }

    @Override
    public Set<EntityController> getEntitiesControllers() {
        return Collections.unmodifiableSet(this.entitiesControllers);
    }

    @Override
    public PlayerStatistics getPlayerStatistic() {
        return this.playerStatistics;
    }

    private static Point2D invertY(final Point2D pt) {
        return new Point2D(pt.getX(), -pt.getY());
    }

    private void loadObjects(final ObjectGroup layer) {
        //final ObjectGroup objLayer = layer;
        String a = layer.getName().trim().toLowerCase(Locale.UK);

        if (layer.getName().trim().toLowerCase(Locale.UK).equals(SOLID_OBJECTS)) {

            this.loadSolidObjects(layer);
        }
        if (layer.getName().trim().toLowerCase(Locale.UK).equals(ENTITY_OBJECTS)) {

            this.loadEntityObjects(layer);
        }
    }
    private void loadSolidObjects(final ObjectGroup layer) {

        layer.forEach(solidObject -> {
            final String type = solidObject.getType();

            if (type.equals("VERTICAL")) {
                final Pair<Point2D, Dimension2D> pos = mapPositionToWorldVerticalBlocks(this.map, solidObject.getX(), solidObject.getY(),
                        solidObject.getWidth(), solidObject.getHeight());
                entitySpawner.spawn(pos.getKey(), pos.getValue());

            } else if (type.equals("HORIZONTAL")) {
                final Pair<Point2D, Dimension2D> pos = mapPositionToWorldHorizontalBlocks(this.map, solidObject.getX(), solidObject.getY(),
                        solidObject.getWidth(), solidObject.getHeight());
                entitySpawner.spawn(pos.getKey(), pos.getValue());

            }

           // final Pair<Point2D, Dimension2D> pos = mapPositionToWorld(this.map, solidObject.getX(), solidObject.getY(),
             //       solidObject.getWidth(), solidObject.getHeight());
            //entitySpawner.spawn(pos.getKey(), pos.getValue());
        });
    }

    private void loadEntityObjects(final ObjectGroup layer) {
        layer.forEach(entityObject -> {
            final Point2D position = invertY(new Point2D(entityObject.getX() / POSITION_DIVISOR_SPAWN_X, entityObject.getY() / POSITION_DIVISOR_SPAWN_Y));
                this.gameField.getWalls().forEach(w -> System.out.println(w.getBody().getPosition()));
            this.gameField.getWalls().forEach(w -> System.out.println(w.getBody().getDimension()));


            final String type = entityObject.getType();
            System.out.println(type + position);
            Entity entity;
            switch (EntityType.valueOf(type)) {
                //creation of the player
                case PLAYER:
                    if (this.gameStatistics.getCurrentLevel() == FIRST_LEVEL) {
                        player = entitySpawner.spawn(EntityType.PLAYER.toString(), position);
                        final PlayerController controller = new PlayerController(player, gameFieldView.getEntityViewFactory().createPlayerView());
                        gameFieldView.setPlayerInputListener(controller);
                        entitiesControllers.add(controller);
                        playerStatistics = new PlayerStatisticsImpl(player);
                    }
                    //if it is not the first level it doesn't recreate the player
                    break;
                //creation of all power ups, coins and enemies
                case COIN:
                    entity = entitySpawner.spawn(EntityType.COIN.toString(), position);
                    entitiesControllers.add(new LifelessEntityController(entity, gameFieldView.getEntityViewFactory().createCoinView()));
                    break;
                case ALIEN:
                    for (int i = 0; i < entitySpawner.getEnemiesNumber(this.gameStatistics.getCurrentLevel()); i++) {
                        entity = entitySpawner.spawn(EntityType.ALIEN.toString(), position);
                        entitiesControllers.add(new AliveEntityController(entity, gameFieldView.getEntityViewFactory().createAlienView()));
                    }
                    break;
                case DOUBLESPEED:
                    entity = entitySpawner.spawn(EntityType.DOUBLESPEED.toString(), position);
                    entitiesControllers.add(new LifelessEntityController(entity, gameFieldView.getEntityViewFactory().createDoubleSpeedView()));
                    break;

                case DOUBLEDAMAGE:
                    entity = entitySpawner.spawn(EntityType.DOUBLEDAMAGE.toString(), position);
                    entitiesControllers.add(new LifelessEntityController(entity, gameFieldView.getEntityViewFactory().createDoubleDamageView()));
                    break;

                case SHIELD:
                    entity = entitySpawner.spawn(EntityType.SHIELD.toString(), position);
                    entitiesControllers.add(new LifelessEntityController(entity, gameFieldView.getEntityViewFactory().createShieldView()));
                    break;

                default:
                    break;
            }
        });
    }

    private void loadTiles(final TileLayer layer) {
        gameFieldView.showField(layer, new Point2D(TOP_LEFT_X, TOP_LEFT_Y), new Dimension2D(TILE_SIZE, TILE_SIZE));
    }
    private Pair<Point2D, Dimension2D> mapPositionToWorld(final Map map, final double x, final double y,
                                                          final double width, final double height) {
        final Dimension2D dim = new Dimension2D(width / map.getTileWidth(), height / map.getTileHeight());
        final Point2D pos = new Point2D(x / map.getTileWidth() + dim.getWidth() / 2,
                -(y / map.getTileHeight() + dim.getHeight()));
        return new Pair<>(pos, dim);
    }

    private Pair<Point2D, Dimension2D> mapPositionToWorldVerticalBlocks(final Map map, final double x, final double y,
                                                          final double width, final double height) {
        final Dimension2D dim = new Dimension2D(width / map.getTileWidth(), height / map.getTileHeight());
        final Point2D pos = new Point2D(x / map.getTileWidth() + dim.getWidth() / 2,
                -(y / map.getTileHeight() + dim.getHeight()));
        return new Pair<>(pos, dim);
    }

    private Pair<Point2D, Dimension2D> mapPositionToWorldHorizontalBlocks(final Map map, final double x, final double y,
                                                          final double width, final double height) {
        final Dimension2D dim = new Dimension2D(width / map.getTileWidth(), height / map.getTileHeight());
        final Point2D pos = new Point2D(x / map.getTileWidth() ,
                -(y / map.getTileHeight() + dim.getHeight() / 2));
        return new Pair<>(pos, dim);
    }

}