package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;
import it.unibo.oop.bbgmm.Utilities.Pair;

import java.awt.*;

public class CollisionComponent extends AbstractEntityComponent implements Collidable {

    private final CollisionLabel label;
    private final EventSource<Collision> collisionEvent;
    private final Rectangle shape;

    public CollisionComponent(Entity owner, final Rectangle shape, final CollisionLabel label){

        this.shape = shape;
        this.label = label;
        this.collisionEvent = new EventSource<Collision>();
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

    }
}
