package it.unibo.oop.bbgmm.entity.collision;

import java.util.ArrayList;
import java.util.List;

/**
 * It search between {@link Collidable} pairs end notify them if they are colliding.
 */
public final class CollisionSupervisorImpl implements CollisionSupervisor {

    private final List<Collidable> collidableComponents;

    /**
     * {@link CollisionSupervisor} constructor.
     */
    public CollisionSupervisorImpl() {
        this.collidableComponents = new ArrayList<>();
    }

    @Override
    public void searchCollision() {
        for (int i = 0; i < this.collidableComponents.size(); i++) {
            final Collidable coll1 = collidableComponents.get(i);
            for (int j = i + 1; j < this.collidableComponents.size(); j++) {
                final Collidable coll2 = collidableComponents.get(j);
                verifyCollision(coll1, coll2);
            }
        }
    }

    @Override
    public void addCollisionComponent(final Collidable collisionComponent) {
        this.collidableComponents.add(collisionComponent);
    }

    @Override
    public void removeCollisionComponent(final Collidable collisionComponent) {
        this.collidableComponents.remove(collisionComponent);
    }

    private void verifyCollision(final Collidable coll1, final Collidable coll2) {
        if (coll1.getCollisionLabel().canCollideWith(coll2.getCollisionLabel())
                && coll1.getShape().getBoundsInLocal().intersects(coll2.getShape().getBoundsInLocal())) {
                notifyCollision(coll1, coll2, CollisionLabel.COIN);
                notifyCollision(coll1, coll2, CollisionLabel.SHOT);
                notifyCollision(coll1, coll2, CollisionLabel.POWER);
                notifyCollision(coll1, coll2, CollisionLabel.ALIEN);
        }
    }

    private void notifyCollision(final Collidable c1, final Collidable c2, final CollisionLabel label) {
        if (c1.getCollisionLabel().equals(label) || c2.getCollisionLabel().equals(label)) {
            if (c1.getCollisionLabel().equals(label)) {
                c1.notifyCollision(new Collision(c2));
            } else {
                c2.notifyCollision(new Collision(c1));
            }
        }
    }
}
