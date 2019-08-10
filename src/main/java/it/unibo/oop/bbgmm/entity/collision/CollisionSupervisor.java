package it.unibo.oop.bbgmm.entity.collision;

public interface CollisionSupervisor {

    void searchCollision();

    void addCollisionComponent(Collidable collidableComponent);

    void removeCollisionComponent(Collidable collidableComponent);
}
