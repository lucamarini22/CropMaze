package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Represent a wall.
 */
public class Wall extends AbstractEntity {

    private static final double WIDTH = 1.1;
    private static final double HEIGHT = 1.2;
    private static Dimension2D SIZE;

    /**
     *
     * @param bodyBuilder
     *      The bodybuilder of the wall
     * @param position
     *      The position of the wall
     */
    public Wall(final BodyBuilder bodyBuilder, final Point2D position, final Dimension2D dimension) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(dimension)
                .setMovable(false)
                .build());
    }
}
