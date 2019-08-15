package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Point2D;

/**
 * Interface for player input listener.
 */
public interface PlayerInputListener {
    /**
     * Method called when the player wants to move.
     * @param vector
     *      The direction vector.
     */
    void move(Point2D vector);

    /**
     * Method called when the player wants to shoot.
     * @param direction
     *      The direction where the bullet is shot.
     */
    void shoot(Direction direction);
}
