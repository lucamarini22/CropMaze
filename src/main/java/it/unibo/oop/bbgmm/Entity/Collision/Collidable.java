package it.unibo.oop.bbgmm.Entity.Collision;

import it.unibo.oop.bbgmm.Entity.Component.EntityComponent;
import it.unibo.oop.bbgmm.Entity.Event;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public interface Collidable extends EntityComponent {

    Rectangle getShape();

    Pair<Double, Double> getPosition();

    CollisionLabel getCollisionLabel();

    void notifyCollision(Collision collision);

    Event<Collision> getEvent();
}
