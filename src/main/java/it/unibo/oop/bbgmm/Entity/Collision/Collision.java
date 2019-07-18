package it.unibo.oop.bbgmm.Entity.Collision;

import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;

public class Collision {

    private final CollisionComponent object;

    public Collision(final CollisionComponent object){
        this.object = object;
    }

    public CollisionComponent getCollisionComponent() {
        return object;
    }
}
