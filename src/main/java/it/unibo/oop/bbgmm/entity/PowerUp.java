package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import it.unibo.oop.bbgmm.entity.component.CollisionComponent;
import it.unibo.oop.bbgmm.entity.component.PickupableComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Represent a Power Up.
 */
public class PowerUp extends AbstractEntity {

    private static final Dimension2D SIZE = new Dimension2D(3.1, 3.2);

    /**
     *
     * @param bodyBuilder
     *      the related {@link BodyBuilder} object
     * @param position
     *      the position
     * @param power
     *      the PowerUp's power
     */
    public PowerUp(final BodyBuilder bodyBuilder, final Point2D position, final Power power) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setDirection(Direction.NOTHING)
                .setMovable(false)
                .build());
        add(new CollisionComponent(new Rectangle(position.getX(), position.getY(),
                SIZE.getWidth(), SIZE.getHeight()), CollisionLabel.POWER));
        add(new PickupableComponent(power));
    }
}
