package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public interface EntityBody extends EntityComponent{

    /**
     * @return The position
     */
    Point2D getPosition();

    /**
     *
     * @return Body dimension
     */
    Dimension2D getDimension();

    /**
     *
     * @return Body direction
     */
    Direction getDirection();

    /**
     *
     * @return Body shape
     */
    Rectangle2D getShape();

    /**
     *
     * @return if the entity can move
     */
    boolean canMove();

}
