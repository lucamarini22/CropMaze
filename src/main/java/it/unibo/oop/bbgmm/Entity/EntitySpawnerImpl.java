package it.unibo.oop.bbgmm.Entity;

import javafx.geometry.Point2D;
import org.apache.commons.lang.NullArgumentException;

/**
 * Implementation of EntitySpawner.
 */
public final class EntitySpawnerImpl implements EntitySpawner {
    private static final String PLAYER = "player";
    private static final String COIN = "coin";
    private static final String ALIEN = "alien";
    private final EntityFactory entityFactory;
    private final GameField gameField;

    /**
     * Constructor of EntitySpawner.
     *
     * @param entityFactory
     *      Factory of entities
     * @param gameField
     *      The {@link GameField}
     */
    public EntitySpawnerImpl(final EntityFactory entityFactory, final GameField gameField) {
        this.entityFactory = entityFactory;
        this.gameField = gameField;
    }

    @Override
    public Entity spawn(final String entityType, final Point2D position) {
        switch (entityType) {
            case PLAYER:
                return gameField.addEntity(entityFactory.createPlayer(position));
            case ALIEN:
                return gameField.addEntity(entityFactory.createEnemy(position));
            case COIN:
                return gameField.addEntity(entityFactory.createCoin(position));
            default:
                break;
        }
        throw new NullArgumentException("Wrong argument");
    }
}
