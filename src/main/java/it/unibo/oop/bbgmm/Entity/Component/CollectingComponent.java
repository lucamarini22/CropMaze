package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collision;

public class CollectingComponent extends AbstractEntityComponent {

    public CollectingComponent(){
        this.getOwner().ifPresent(owner -> owner.get(CollisionComponent.class).ifPresent(
                c -> c.getEvent().register(this::collect)));
    }

    private void collect(Collision collision){
            collision.getCollisionComponent().getOwner().ifPresent(owner -> owner.get(
                    Bag.class).ifPresent(b -> b.addMoney(1)));
    }

    @Override
    public void update(double delta) {

    }
}
