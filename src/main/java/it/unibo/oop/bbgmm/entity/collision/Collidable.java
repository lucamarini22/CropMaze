package it.unibo.oop.bbgmm.entity.collision;

import it.unibo.oop.bbgmm.entity.component.EntityComponent;
import it.unibo.oop.bbgmm.entity.Event;
import it.unibo.oop.bbgmm.utilities.Pair;
import javafx.scene.shape.Rectangle;

public interface Collidable extends EntityComponent {

    Rectangle getShape();

    Pair<Double, Double> getPosition();

    CollisionLabel getCollisionLabel();

    void notifyCollision(Collision collision);

    Event<Collision> getEvent();
}
