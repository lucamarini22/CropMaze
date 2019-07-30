package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Factory implementation of the AbstractFactory EntityFactory.
 */
public final class EntityFactoryImpl implements EntityFactory {

    private static final int COIN_VALUE = 10;
    private static final double TIMEOUT = 0.1;


    // da mettere e gettare in una class difficoltà
    private static final int PLAYER_HEALTH = 100;
    private static final int ENEMY_HEALTH = 20;
    //


    @Override
    public Player createPlayer(final Point2D position) {
        return new Player(new BodyBuilder(), position, PLAYER_HEALTH);
    }

    @Override
    public Alien createEnemy(final Point2D position) {
        return new Alien(new BodyBuilder(), position, ENEMY_HEALTH);
    }

    @Override
    public Coin createCoin(final Point2D position) {
        return new Coin(new BodyBuilder(), position, COIN_VALUE);
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
