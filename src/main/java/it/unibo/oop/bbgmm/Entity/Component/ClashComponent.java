package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.EntityType;

public class ClashComponent extends AbstractEntityComponent implements Clash{

    private boolean registered = false;
    public ClashComponent(){
    }

    private void hit(Collision collision) {

        collision.getCollisionComponent().getOwner().ifPresent( owner ->
            owner.get(Life.class).ifPresent(
                    life -> {
                        this.getOwner().ifPresent(o -> o.get(Damage.class).ifPresent(
                                d -> {
                                    int damage = d.getDamage();
                                    life.damaged(damage);
                                }));
                        if(life.isDead()){
                            ((Entity) owner).removeEntity();
                        }
                    }));
        this.getOwner().ifPresent(o -> ((Entity) o).get(Collidable.class).ifPresent(
                c -> {
                    if(c.getCollisionLabel().equals(CollisionLabel.SHOT)){
                        this.getOwner().ifPresent(owner -> owner.removeEntity());
                    }
                }
        ));
    }

    @Override
    public void update(double delta) {
        if(!registered) {
            this.getOwner().ifPresent(owner -> owner.get(Collidable.class).ifPresent(
                    c -> c.getEvent().register(this::hit)));
            registered = true;
        }
    }

}
