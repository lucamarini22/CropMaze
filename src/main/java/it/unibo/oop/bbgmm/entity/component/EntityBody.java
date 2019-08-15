package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Interface fot body component.
 */
public interface EntityBody extends EntityComponent {

    /**
     * @param position
     *      The position to add
     */
    void addPosition(Point2D position);

    /**
     * @param direction
     *      The new direction
     */
    void changeDirection(Direction direction);
    /**
     * @return The position
     */
    Point2D getPosition();

    /**
     * @return Body dimension
     */
    Dimension2D getDimension();

    /**
     * @return Body direction
     */
    Direction getDirection();

    /**
     * @return Body shape
     */
    Rectangle getShape();

}
