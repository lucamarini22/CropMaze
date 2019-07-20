package it.unibo.oop.bbgmm.Control;

import javafx.geometry.Point2D;

public interface PlayerInputListener {
    /**
     * Method called when the player wants to move
     * @param vector
     *      The direction vector
     */
    void move(Point2D vector);
}
