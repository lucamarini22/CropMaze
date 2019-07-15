package it.unibo.oop.bbgmm.Entity.Collision;

import java.util.ArrayList;
import java.util.List;

public class CollisionSupervisorImpl implements CollisonSupervisor {

    private final List<Collidable> collidableComponents;

    public CollisionSupervisorImpl(){
        this.collidableComponents = new ArrayList<>();
    }


    @Override
    public void searchCollision() {
        for(int i = 0; i < this.collidableComponents.size(); i++){
            for (int j = i + 1; j< this.collidableComponents.size(); j++){
                final Collidable collidable1 = collidableComponents.get(i);
                final Collidable collidable2 = collidableComponents.get(j);
                verifyCollision(collidable1, collidable2);
            }
        }
    }

    @Override
    public void addCollidableComponent(Collidable collidable) {
        this.collidableComponents.add(collidable);
    }

    @Override
    public void removeCollidableComponent(Collidable collidable) {
        this.collidableComponents.remove(collidable);
    }

    private void verifyCollision(final Collidable collidable1, final Collidable collidable2){
        if(collidable1.getCollisionLabel().canCollideWith(collidable2.getCollisionLabel())){
            if(collidable1.getShape().intersects(collidable2.getShape())) {
                collidable1.notifyCollision(new Collision(collidable2));
                collidable2.notifyCollision((new Collision(collidable1)));
            }
        }
    }
}
