package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.Collision;
import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.Event;
import it.unibo.oop.bbgmm.entity.EventSource;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * It represents a component that makes the Entity able to collide.
 */
public final class CollisionComponent extends AbstractEntityComponent implements Collidable {

    private final CollisionLabel label;
    private final EventSource<Collision> collisionEvent;
    private Rectangle shape;

    /**
     * Creates a new Collision component.
     * @param shape
     *      the shape of the Entity
     * @param label
     *      the {@link CollisionLabel}
     */
    public CollisionComponent(final Rectangle shape, final CollisionLabel label) {
        super();
        this.shape = shape;
        this.label = label;
        this.collisionEvent = new EventSource<>();
    }
    @Override
    public Rectangle getShape() {
        return this.shape;
    }

    @Override
    public CollisionLabel getCollisionLabel() {
        return this.label;
    }

    @Override
    public void notifyCollision(final Collision collision) {
        this.collisionEvent.trigger(collision);
    }

    @Override
    public Event<Collision> getEvent() {
        return this.collisionEvent;
    }

    @Override
    public void update(final double delta) {
        final Point2D pos = this.getOwner().get().getBody().getPosition();
        this.shape = new Rectangle(pos.getX(), pos.getY(), shape.getWidth(), shape.getHeight());
    }
}
