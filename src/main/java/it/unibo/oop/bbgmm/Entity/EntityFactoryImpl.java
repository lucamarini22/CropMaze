package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Factory implementation of the AbstractFactory EntityFactory.
 */
public final class EntityFactoryImpl implements EntityFactory {
    private static final double TIMEOUT = 10;

    private final GameField gameField;
    private final EntityStatistics entityStatistics;
    private final GameStatistics gameStatistics;
    private Player player;

    /**
     * Constructor of {@link EntityFactoryImpl}.
     * @param gameField
     *      {@link GameField} instance
     * @param entityStatistics
     *      Statistics of the various entities
     * @param gameStatistics
     *      Statistics of the game
     */
    public EntityFactoryImpl(final GameField gameField, final EntityStatistics entityStatistics, final GameStatistics gameStatistics) {
        this.gameField = gameField;
        this.entityStatistics = entityStatistics;
        this.gameStatistics = gameStatistics;
    }

    @Override
    public Player createPlayer(final Point2D position) {
        this.player = new Player(new BodyBuilder(), position, entityStatistics.getPlayerHealth(), this.gameField);
        return this.player;
    }

    @Override
    public Alien createEnemy(final Point2D position) {
        if (this.player == null) {
            throw new NullPointerException("Error: The Player is not present");
        }
        return new Alien(new BodyBuilder(), position, entityStatistics.getEnemyHealth(this.gameStatistics.getCurrentLevel()), gameField.getWalls(), this.player);
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
