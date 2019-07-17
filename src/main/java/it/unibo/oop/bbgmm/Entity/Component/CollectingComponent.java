package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Entity;

public class CollectingComponent extends AbstractEntityComponent {

    public CollectingComponent(){
        this.getOwner().ifPresent(owner -> owner.get(CollisionComponent.class).ifPresent(
                c -> c.getEvent().register(this::collect)));
    }

    private void collect(Collision collision){
            //collision.getCollisionComponent().getOwner().ifPresent(owner ->
              //      owner.get(Bag.class).ifPresent(
        //
        //
        //      );
    }

    @Override
    public void update(double delta) {

    }
}
