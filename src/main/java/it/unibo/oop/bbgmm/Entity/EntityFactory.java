package it.unibo.oop.bbgmm.Entity;

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

    //power ups
}
