package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Point2D;

public interface PlayerInputListener {
    /**
     * Method called when the player wants to move
     * @param vector
     *      The direction vector
     */
    void move(Point2D vector);

    /**
     * Method called when the player wants to shoot
     * @param direction
     *      the direction where the bullet is shot
     */
    void shoot (Direction direction);
}
