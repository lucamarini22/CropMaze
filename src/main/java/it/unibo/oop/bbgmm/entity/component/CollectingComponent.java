package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.Collision;
import it.unibo.oop.bbgmm.entity.Entity;

/**
 * It represents a component that makes an Entity able to collect coin.
 */
public final class CollectingComponent extends AbstractEntityComponent implements Collector {
    private static final int INCREMENT_MONEY = 1;
    private boolean registered;

    private void collect(final Collision collision) {
          collision.getCollisionComponent().getOwner().ifPresent(owner -> owner.get(
                  Bag.class).ifPresent(b -> b.addMoney(INCREMENT_MONEY)));
          this.getOwner().get().removeEntity();
    }

    @Override
    public void update(final double delta) {
        if (!registered) {
            this.getOwner().ifPresent(o -> ((Entity) o).get(Collidable.class).get().getEvent().register(this::collect));
            registered = true;
        }
    }
}
