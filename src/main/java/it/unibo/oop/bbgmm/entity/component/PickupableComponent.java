package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.Collision;
import it.unibo.oop.bbgmm.entity.Power;
import it.unibo.oop.bbgmm.utilities.Temporary;

import java.util.List;

/**
 * It represents a component that makes an Entity able to collect pickup.
 */
public final class PickupableComponent extends AbstractEntityComponent implements Collector {

    private final Power power;
    private boolean registered;

    /**
     * Create a new PickupableComponent.
     * @param power
     *      the power of the pickUp
     */
    public PickupableComponent(final Power power) {
        super();
        this.power = power;
    }

    private void pickUp(final Collision collision) {

        collision.getCollisionComponent().getOwner().ifPresent(
                owner -> owner.get(Bag.class).ifPresent(
                        bag -> {
                            final List<Power> powers = bag.getPowers();
                            boolean found = false;
                            for (final Power p : powers) {
                                if (p.getPowerTag() == power.getPowerTag()) {
                                    ((Temporary) p).addTime(((Temporary) power).getRemainingTime());
                                    found = true;
                                }
                            }
                            if (!found) {
                                powers.add(power);
                                power.activate(collision.getCollisionComponent().getOwner().get());
                            }
                        })
        );
        this.getOwner().get().removeEntity();
    }
    @Override
    public void update(final double delta) {
        if (!registered) {
            this.getOwner().ifPresent(owner -> owner.get(Collidable.class).ifPresent(
                    c -> c.getEvent().register(this::pickUp)));
            registered = true;
        }
    }
}
