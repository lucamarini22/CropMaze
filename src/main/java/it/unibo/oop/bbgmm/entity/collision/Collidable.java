package it.unibo.oop.bbgmm.entity.collision;

import it.unibo.oop.bbgmm.entity.component.EntityComponent;
import it.unibo.oop.bbgmm.entity.Event;
import javafx.scene.shape.Rectangle;

/**
 * Represents a component that can collides with other components.
 */
public interface Collidable extends EntityComponent {

    /**
     * Return the shape.
     * @return
     *      the shape of the object
     */
    Rectangle getShape();

    /**
     * Returns the {@link CollisionLabel} of the object.
     * @return
     *      the collision label of the object
     */
    CollisionLabel getCollisionLabel();

    /**
     * Tells this object that is colliding with another object.
     * @param collision
     *      the {@link Collision} data
     */
    void notifyCollision(Collision collision);

    /**
     * Returns the collision Event.
     * @return
     *      the collision event
     */
    Event<Collision> getEvent();
}
