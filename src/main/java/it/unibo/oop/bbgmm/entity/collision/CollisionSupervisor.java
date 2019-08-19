package it.unibo.oop.bbgmm.entity.collision;

/**
 * It is responsible to detect collision between collidable components.
 */
public interface CollisionSupervisor {

    /**
     * Search between the collidable component registered there is a collision between couples.
     */
    void searchCollision();

    /**
     * Add new {@link Collidable}.
     * @param collidableComponent
     *      the {@link Collidable} to be added
     */
    void addCollisionComponent(Collidable collidableComponent);

    /**
     * Remove the given {@link Collidable}.
     * @param collidableComponent
     *      the {@link Collidable} to be removed
     */
    void removeCollisionComponent(Collidable collidableComponent);
}
