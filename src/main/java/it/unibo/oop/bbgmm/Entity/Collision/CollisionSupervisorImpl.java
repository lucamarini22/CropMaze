package it.unibo.oop.bbgmm.Entity.Collision;

import it.unibo.oop.bbgmm.Entity.Component.CollisionComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionSupervisorImpl implements CollisionSupervisor {

    private final List<CollisionComponent> collidableComponents;

    public CollisionSupervisorImpl(){
        this.collidableComponents = new ArrayList<>();
    }


    @Override
    public void searchCollision() {
        for(int i = 0; i < this.collidableComponents.size(); i++){
            for (int j = i + 1; j< this.collidableComponents.size(); j++){
                final CollisionComponent coll1 = collidableComponents.get(i);
                final CollisionComponent coll2 = collidableComponents.get(j);
                verifyCollision(coll1, coll2);
            }
        }
    }

    @Override
    public void addCollisionComponent(CollisionComponent collisionComponent) {
        this.collidableComponents.add(collisionComponent);
    }

    @Override
    public void removeCollisionComponent(CollisionComponent collisionComponent) {
        this.collidableComponents.remove(collisionComponent);
    }

    private void verifyCollision(final CollisionComponent coll1, final CollisionComponent coll2){
        if(coll1.getCollisionLabel().canCollideWith(coll2.getCollisionLabel())){
            if(coll1.getShape().intersects(coll2.getShape())) {
                notifyCollision(coll1, coll2, CollisionLabel.COIN);
                notifyCollision(coll1, coll2, CollisionLabel.SHOT);
                notifyCollision(coll1, coll2, CollisionLabel.POWER);
                notifyCollision(coll1, coll2, CollisionLabel.ALIEN);
            }
        }
    }

    private void notifyCollision(final CollisionComponent c1, final CollisionComponent c2, CollisionLabel label){
        if(c1.getCollisionLabel().equals(label) || c2.getCollisionLabel().equals(label)){
            if (c1.getCollisionLabel().equals(label)) {
                c1.notifyCollision(new Collision(c2));
            }
            else
            {
                c2.notifyCollision(new Collision(c1));
            }
        }
    }
}
