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
    private boolean movable = false;


    /**
     * Method for set direction.
     * @param direction
     *          new direction  to set.
     * @return
     *          new direction
     */
    public BodyBuilder setDirection(final Direction direction) {
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
    public BodyBuilder setDimension(final Dimension2D dimension) {
        this.dimension = dimension;
        return this;
    }

    /**
     * Method for set if is movable.
     * @param movable
     *          boolean.
     * @return
     *          if is movable or not.
     */
    public BodyBuilder setMovable(final boolean movable) {
        this.movable = movable;
        return this;
    }

    /**
     * MEthos fot set the position.
     * @param position
     *          new position to set.
     * @return
     *          new position.
     */
    public BodyBuilder setPosition(final Point2D position) {
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
        } else {
            final Body body = new Body(this.position, this.dimension, this.direction, this.movable);
            return body;
        }
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
