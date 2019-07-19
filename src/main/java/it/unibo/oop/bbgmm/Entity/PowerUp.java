package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;
import it.unibo.oop.bbgmm.Entity.Component.PickupableComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class PowerUp extends AbstractEntity {

    private static final Dimension2D SIZE = new Dimension2D(1.1,1.2);
    public PowerUp(BodyBuilder bodyBuilder, final Point2D position, final Power power) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setMovable(false)
                .build());
        add(new CollisionComponent(new Rectangle2D(position.getX(), position.getY(),
                SIZE.getWidth(), SIZE.getHeight()), CollisionLabel.POWER));
        add(new PickupableComponent(power));
    }
}