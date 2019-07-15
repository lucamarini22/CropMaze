package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Represent a wall.
 */

public class Wall extends AbstractEntity {

    private static final double WIDTH = 1.1;
    private static final double HEIGHT = 1.2;
    private static final Dimension2D SIZE = new Dimension2D(WIDTH, HEIGHT);

    /**
     *
     * @param bodyBuilder
     *      The bodybuilder of the wall
     * @param position
     *      The position of the wall
     */
    public Wall(final BodyBuilder bodyBuilder, final Point2D position) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .build());
    }
}
