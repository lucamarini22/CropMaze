package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Represent a wall.
 */
public class Wall extends AbstractEntity {

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
                .setDirection(Direction.NOTHING)
                .setPosition(position)
                .setDimension(dimension)
                .setMovable(false)
                .build());
    }

    @Override
    public String toString(){
        return "Wall";
    }
}
