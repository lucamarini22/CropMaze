package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Event;
import it.unibo.oop.bbgmm.Entity.EventSource;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.geometry.Rectangle2D;

import java.awt.*;

public class CollisionComponent extends AbstractEntityComponent implements Collidable {

    private final CollisionLabel label;
    private final EventSource<Collision> collisionEvent;
    private final Rectangle2D shape;

    public CollisionComponent(Entity owner, final Rectangle2D shape, final CollisionLabel label){

        this.shape = shape;
        this.label = label;
        this.collisionEvent = new EventSource<Collision>();
    }
    @Override
    public Rectangle2D getShape() {
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
