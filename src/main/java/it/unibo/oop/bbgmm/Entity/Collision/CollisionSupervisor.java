package it.unibo.oop.bbgmm.Entity.Collision;

import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;

public interface CollisionSupervisor {

    void searchCollision();

    void addCollisionComponent(CollisionComponent collisionComponent);

    void removeCollisionComponent(CollisionComponent collisionComponent);
}
