package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Represent a wall.
 */
public final class Wall extends AbstractEntity {

    /**
     *
     * @param bodyBuilder
     *      The bodybuilder of the wall
     * @param position
     *      The position of the wall
     * @param dimension
     *      The dimension of the wall
     */
    public Wall(final BodyBuilder bodyBuilder, final Point2D position, final Dimension2D dimension) {
        super(bodyBuilder
                .bodyDirection(Direction.NOTHING)
                .bodyPosition(position)
                .bodyDimension(dimension)
                .build());
    }

    @Override
    public String toString() {
        return "Wall";
    }
}
