package it.unibo.oop.bbgmm.Entity.Collision;

public interface CollisonSupervisor {

    void searchCollision();

    void addCollidableComponent(Collidable collidable);

    void removeCollidableComponent(Collidable collidable);
}
