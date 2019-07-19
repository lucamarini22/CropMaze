package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;

public class ClashComponent extends AbstractEntityComponent {

    public ClashComponent(){
        this.getOwner().ifPresent(owner -> owner.get(CollisionComponent.class).ifPresent(
                c -> c.getEvent().register(this::hit)));
    }

    private void hit(Collision collision) {
        collision.getCollisionComponent().getOwner().ifPresent( owner ->
            owner.get(LifeComponent.class).ifPresent(
                    life -> {
                        life.damaged(10);
                    }));
        if(collision.getCollisionComponent().getCollisionLabel().equals(CollisionLabel.SHOT))
            this.getOwner().ifPresent(owner ->(owner).destroy());
    }

    @Override
    public void update(double delta) {
    }

}