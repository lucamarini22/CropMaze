package it.unibo.oop.bbgmm.entity.component;


import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


/**
 * Body Builder class.
 */
public class BodyBuilder {
    private Dimension2D dimension;
    private Point2D position;
    private Direction direction;

    /**
     * Method for set direction.
     * @param direction
     *          new direction  to set.
     * @return
     *          new direction
     */
    public BodyBuilder bodyDirection(final Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Method for set dimension.
     * @param dimension
     *          new dimension  to set.
     * @return
     *          new dimension.
     */
    public BodyBuilder bodyDimension(final Dimension2D dimension) {
        this.dimension = dimension;
        return this;
    }

    /**
     * MEthos fot set the position.
     * @param position
     *          new position to set.
     * @return
     *          new position.
     */
    public BodyBuilder bodyPosition(final Point2D position) {
        this.position = position;
        return this;
    }

    /**
     * Build entity.
     * @return
     *      entity body.
     */
    public Body build() {
        if (this.checkBuild()) {
            throw new IllegalStateException("The build is incomplete");
        }
        return new Body(this.position, this.dimension, this.direction);

    }

    /**
     * Check if build is made efficiently.
     * @return
     *          true if build is incomplete, false otherwise.
     */
    private boolean checkBuild() {
        return dimension == null || this.position == null || this.direction == null;
    }


}
