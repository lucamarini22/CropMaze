package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.Entity;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * component used to destroy the Bullet after a number of steps.
 */
public final class LimitedBulletFeet extends Feet {

    private static final int STEP = 1;
    private final Life lifeComponent;
    private final Weapon weapon;
    private final Point2D distanceVector;

    /**
     * Constructor for LimitedBulletFeet.
     * @param weapon
     *          The weapon of the player
     * @param direction
     *          The direction of the bullet
     * @param walkingSpeed
     *          speed for the movement
     * @param lifeComponent
     *          lifeComponent of the bullet
     * @param walls
     *          set of walls
     */
    public LimitedBulletFeet(final Weapon weapon, final Direction direction, final double walkingSpeed, final Life lifeComponent, final Set<Entity> walls) {
        super(walkingSpeed, walls);
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
        this.distanceVector = calculateVector(direction);
    }

    @Override
    public void move(final Point2D distanceVector) {
        if (lifeComponent.isAlive()) {
            super.move(distanceVector);
            if (getPosition().equals(Point2D.ZERO)) {
                remove();
            } else {
                lifeComponent.damaged(STEP);
            }
        } else {
            remove();
        }
    }

    @Override
    public void update(final double delta) {
        move(distanceVector);
    }

    private void remove() {
        weapon.removeBullet((Bullet) getOwner().get());
        getOwner().get().removeEntity();
    }
}
