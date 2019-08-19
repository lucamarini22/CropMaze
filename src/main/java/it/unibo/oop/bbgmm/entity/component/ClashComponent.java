package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.Collision;
import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.Entity;

/**
 * A component that manage a clash collision with another entity.
 */
public final class ClashComponent extends AbstractEntityComponent implements Clash {

    private boolean registered;

    private void hit(final Collision collision) {

        collision.getCollisionComponent().getOwner().ifPresent(owner ->
            owner.get(Life.class).ifPresent(
                    life -> {
                        this.getOwner().ifPresent(o -> o.get(Damage.class).ifPresent(
                                d -> {
                                    final int damage = d.getDamage();
                                    life.damaged(damage);
                                }));
                        if (life.isDead()) {
                            ((Entity) owner).removeEntity();
                        }
                    }));
        this.getOwner().ifPresent(o -> ((Entity) o).get(Collidable.class).ifPresent(
                c -> {
                    if (c.getCollisionLabel().equals(CollisionLabel.SHOT)) {
                        this.getOwner().ifPresent(owner -> owner.removeEntity());
                    }
                }
        ));
    }

    @Override
    public void update(final double delta) {
        if (!registered) {
            this.getOwner().ifPresent(owner -> owner.get(Collidable.class).ifPresent(
                    c -> c.getEvent().register(this::hit)));
            registered = true;
        }
    }
}
