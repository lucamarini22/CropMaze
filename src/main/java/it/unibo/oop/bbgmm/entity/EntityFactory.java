package it.unibo.oop.bbgmm.entity;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Abstract Factory for the entities.
 */
public interface EntityFactory {
    /**
     * @param position
     *      Position where the player spawns
     * @return the player
     */
    Player createPlayer(Point2D position);

    /**
     * @param position
     *      Position where the enemy spawn
     * @return an enemy (alien)
     */
    Alien createEnemy(Point2D position);

    /**
     * @param position
     *      Position where the coin spawns
     * @return the coin
     */
    Coin createCoin(Point2D position);

    /**
     * @param position
     *      Position where the wall spawns
     * @param dimension
     *      Dimension of the wall
     * @return a wall
     */
    Wall  createWall(Point2D position, Dimension2D dimension);

    /**
     * @param position
     *      Position where the double speed power up spawn
     * @return the {@link TemporaryDoubleSpeed} power up
     */
    PowerUp createDoubleSpeed(Point2D position);

    /**
     * @param position
     *      Position where the double damage power up spawn
     * @return the {@link TemporaryDoubleDamage} power up
     */
    PowerUp createDoubleDamage(Point2D position);

    /**
     * @param position
     *      Position where the shield power up spawn
     * @return the {@link TemporaryShield} power up
     */
    PowerUp createShield(Point2D position);

}
