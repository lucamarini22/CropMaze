package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Optional;


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
     * @return If the body can move
     */
    boolean canMove();

    /**
     *
     * @return Body shape
     */
    Rectangle2D getShape();

}
