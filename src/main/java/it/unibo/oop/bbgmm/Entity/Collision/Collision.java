package it.unibo.oop.bbgmm.Entity.Collision;

import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;

public class Collision {

    private final Collidable object;

    public Collision(final Collidable object){
        this.object = object;
    }

    public Collidable getCollisionComponent() {
        return object;
    }
}
