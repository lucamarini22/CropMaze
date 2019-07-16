package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class EntityFactoryImpl implements EntityFactory {

    // da mettere e gettare in una class difficoltà
    private final int playerHealth = 100;
    private final int enemyHealth = 20;
    //
    private static final int COIN_VALUE = 10;


    @Override
    public Player createPlayer(final Point2D position) {
        return new Player(new BodyBuilder(), position, playerHealth);
    }

    @Override
    public Alien createEnemy(final Point2D position) {
        return new Alien(new BodyBuilder(), position, enemyHealth);
    }

    @Override
    public Coin createCoin(final Point2D position) {
        return new Coin(new BodyBuilder(), position, COIN_VALUE);
    }

    @Override
    public Wall createWall(final Point2D position, final Dimension2D dimension) {
        return new Wall(new BodyBuilder(), position, dimension);
    }
}
