package it.unibo.oop.bbgmm.entity.collision;

public class Collision {

    private final Collidable object;

    public Collision(final Collidable object){
        this.object = object;
    }

    public Collidable getCollisionComponent() {
        return object;
    }
}
