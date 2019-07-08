package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.awt.*;

public interface Collidable {

    Rectangle getShape();

    Pair<Double, Double> getPosition();

    CollisionLabel getCollisionLabel();

    void notifyCollision(Collision collision);

    Event<Collision> getEvent();
}
