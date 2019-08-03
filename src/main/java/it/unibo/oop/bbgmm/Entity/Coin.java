package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Represent a coin entity that the player can collect.
 */
public class Coin extends AbstractEntity {

    private static final double WIDTH = 2.8;
    private static final double HEIGHT = 3.5;
    private static final Dimension2D SIZE = new Dimension2D(WIDTH, HEIGHT);
    /**
     *
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object
     * @param position
     *            The position of the bodyBuilder
     * @param value
     *            The coin value
     */
    public Coin(final BodyBuilder bodyBuilder, final Point2D position,
                final int value) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setMovable(false)
                .setDirection(Direction.NOTHING)
                .build());
    }
}
