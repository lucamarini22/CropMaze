package it.unibo.oop.bbgmm.Entity;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.apache.commons.lang.NullArgumentException;

/**
 * Implementation of EntitySpawner.
 */
public final class EntitySpawnerImpl implements EntitySpawner {

    private static final int ENEMIES_INCREMENT_FACTOR = 4;

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
        switch (EntityType.valueOf(entityType)) {
            case PLAYER:
                return gameField.addEntity(entityFactory.createPlayer(position));
            case ALIEN:
                return gameField.addEntity(entityFactory.createEnemy(position));
            case COIN:
                return gameField.addEntity(entityFactory.createCoin(position));
            case DOUBLE_SPEED:
                return gameField.addEntity(entityFactory.createDoubleSpeed(position));
            case DOUBLE_DAMAGE:
                return gameField.addEntity(entityFactory.createDoubleDamage(position));
            case SHIELD:
                return gameField.addEntity(entityFactory.createShield(position));
            default:
                break;
        }
        throw new NullArgumentException("Wrong argument");
    }

    @Override
    public Entity spawn(final Point2D position, final Dimension2D dimension) {
        return gameField.addEntity(entityFactory.createWall(position, dimension));
    }

    @Override
    public int getEnemiesNumber(final int currentLevel) {
        if (currentLevel % ENEMIES_INCREMENT_FACTOR == 0) {
            return ENEMIES_INCREMENT_FACTOR;
        } else {
            return currentLevel % ENEMIES_INCREMENT_FACTOR;
        }
    }
}
