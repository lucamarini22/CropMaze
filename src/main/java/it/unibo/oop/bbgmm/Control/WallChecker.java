package it.unibo.oop.bbgmm.Control;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Interface to avoid wall collisions
 */
public interface WallChecker {
    /**
     * Check if the entity is going to collide with any wall
     * @param position
     *      The new entity position
     * @return
     *      True if is going to collide with a wall
     *      False otherwise
     */
    boolean willCollide(Point2D position, Dimension2D dimension);
}
