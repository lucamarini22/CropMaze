package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

/**
 * Component used to destroy the Bullet after a number of steps
 */
public class LimitedBulletFeet extends Feet{

    private int steps;
    private Weapon weapon;

    /**
     * @param walkingSpeed entity speed for the movement
     */
    public LimitedBulletFeet(Weapon weapon, double walkingSpeed, int steps) {
        super(walkingSpeed);
        this.steps = steps;
        this.weapon = weapon;
    }

    @Override
    public void move(Direction direction, double speed) {
        if(steps > 0){
            super.move(direction, speed);
        }
        else{
            weapon.removeBullet((Bullet)getOwner().get());
            getOwner().get().destroy();
        }
        steps--;
    }
}
