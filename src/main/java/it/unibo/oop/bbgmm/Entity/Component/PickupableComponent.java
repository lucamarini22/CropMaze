package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collision;

public class PickupableComponent extends AbstractEntityComponent {

    public PickupableComponent(){
        this.getOwner().ifPresent(owner -> owner.get(CollisionComponent.class).ifPresent(
                c -> c.getEvent().register(this::collect)));
    }

    private void collect(Collision collision){

    }
    @Override
    public void update(double delta) {

    }
}
