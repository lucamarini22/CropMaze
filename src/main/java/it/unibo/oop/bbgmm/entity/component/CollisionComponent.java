package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.Collision;
import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.Event;
import it.unibo.oop.bbgmm.entity.EventSource;
import it.unibo.oop.bbgmm.utilities.Pair;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class CollisionComponent extends AbstractEntityComponent implements Collidable {

    private final CollisionLabel label;
    private final EventSource<Collision> collisionEvent;
    private Rectangle shape;

    public CollisionComponent(final Rectangle shape, final CollisionLabel label){

        this.shape = shape;
        this.label = label;
        this.collisionEvent = new EventSource<>();
    }
    @Override
    public Rectangle getShape() {
        return this.shape;
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public CollisionLabel getCollisionLabel() {
        return this.label;
    }

    @Override
    public void notifyCollision(Collision collision) {
        this.collisionEvent.trigger(collision);
    }

    @Override
    public Event<Collision> getEvent() {
        return this.collisionEvent;
    }

    @Override
    public void update(double delta) {
        Point2D pos = this.getOwner().get().getBody().getPosition();
        this.shape = new Rectangle(pos.getX(), pos.getY(), shape.getWidth(), shape.getHeight());
    }
}
