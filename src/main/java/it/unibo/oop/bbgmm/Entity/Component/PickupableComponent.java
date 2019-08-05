package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
import it.unibo.oop.bbgmm.Entity.Collision.Collision;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Power;
import it.unibo.oop.bbgmm.Utilities.Temporary;

import java.util.List;

/**
 * this component is responsible for collecting power
 */

public class PickupableComponent extends AbstractEntityComponent implements Collector{

    private final Power power;
    private boolean registered = false;

    public PickupableComponent(final Power power){
        this.power = power;
    }

    private void pickUp(Collision collision){

        collision.getCollisionComponent().getOwner().ifPresent(
                owner -> owner.get(Bag.class).ifPresent(
                        bag -> {
                            List<Power> powers = bag.getPowers();
                            boolean found = false;
                            for(final Power p : powers){
                                if(p.getPowerTag() == power.getPowerTag()){
                                    ((Temporary) p).addTime(((Temporary) power).getRemainingTime());
                                    found = true;
                                }
                            }
                            if(!found){
                                powers.add(power);
                                power.activate(collision.getCollisionComponent().getOwner().get());
                            }
                        })
        );
        this.getOwner().get().removeEntity();
    }
    @Override
    public void update(double delta) {
        if (!registered) {
            this.getOwner().ifPresent(owner -> owner.get(Collidable.class).ifPresent(
                    c -> c.getEvent().register(this::pickUp)));
            registered = true;
        }
    }
}
