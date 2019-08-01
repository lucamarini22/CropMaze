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
<<<<<<< HEAD
     * @return if the entity can move
     */
    boolean canMove();

=======
     * @return Body shape
     */
    boolean canMove();
>>>>>>> bd1f7c7f44f5bf726dc2b0da076eef275b024594
}
