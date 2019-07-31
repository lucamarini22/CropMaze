package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * Factory implementation of the AbstractFactory EntityFactory.
 */
public final class EntityFactoryImpl implements EntityFactory {
    private static final double TIMEOUT = 0.1;

    private final Set<Entity> walls;

    private final EntityStatistics entityStatistics;
    private final GameStatistics gameStatistics;

    public EntityFactoryImpl(final Set<Entity> walls, final EntityStatistics entityStatistics, final GameStatistics gameStatistics) {
        this.walls = walls;
        this.entityStatistics = entityStatistics;
        this.gameStatistics = gameStatistics;
    }

    @Override
    public Player createPlayer(final Point2D position) {
        return new Player(new BodyBuilder(), position, entityStatistics.getPlayerHealth(), this.walls);
    }

    @Override
    public Alien createEnemy(final Point2D position) {
        return new Alien(new BodyBuilder(), position, entityStatistics.getEnemyHealth(this.gameStatistics.getCurrentLevel()), this.walls);
    }

    @Override
    public Coin createCoin(final Point2D position) {
        return new Coin(new BodyBuilder(), position, entityStatistics.getCoinValue());
    }

    @Override
    public Wall createWall(final Point2D position, final Dimension2D dimension) {
        return new Wall(new BodyBuilder(), position, dimension);
    }
    @Override
    public PowerUp createDoubleSpeed(final Point2D position) {
        return new PowerUp(new BodyBuilder(), position, new TemporaryDoubleSpeed(TIMEOUT));
    }

    @Override
    public PowerUp createDoubleDamage(final Point2D position) {
        return new PowerUp(new BodyBuilder(), position, new TemporaryDoubleDamage(TIMEOUT));
    }

    @Override
    public PowerUp createShield(final Point2D position) {
        return new PowerUp(new BodyBuilder(), position, new TemporaryShield(TIMEOUT));
    }
}
