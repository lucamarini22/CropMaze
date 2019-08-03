package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Component.CollectingComponent;
import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

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
        add(new CollisionComponent(new Rectangle(position.getX(), position.getY(), WIDTH, HEIGHT), CollisionLabel.COIN));
        add(new CollectingComponent());
    }
}
