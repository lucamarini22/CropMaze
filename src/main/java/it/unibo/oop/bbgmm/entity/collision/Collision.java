package it.unibo.oop.bbgmm.entity.collision;

/**
 * Wrapper class which stores data relative to a collision event.
 */
public class Collision {

    private final Collidable object;

    /**
     * Create a new Collision instance.
     * @param object
     *      the other collidable object
     */
    public Collision(final Collidable object) {
        this.object = object;
    }

    /**
     * Returns the other {@link Collidable} which is colliding whit it.
     * @return
     *      the other {@link Collidable} object
     */
    public Collidable getCollisionComponent() {
        return object;
    }
}
