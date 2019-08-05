package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Entity;

public class CollectingComponent extends AbstractEntityComponent implements Collector{

    private boolean registered = false;
    public CollectingComponent(){
    }

    private void collect(Collision collision){
          collision.getCollisionComponent().getOwner().ifPresent(owner -> owner.get(
                    Bag.class).ifPresent(b -> b.addMoney(1)));
          this.getOwner().get().removeEntity();

    }

    @Override
    public void update(double delta) {
        if (!registered) {
            this.getOwner().ifPresent(o -> ((Entity) o).get(Collidable.class).get().getEvent().register(this::collect));
            registered = true;
        }
    }
}
