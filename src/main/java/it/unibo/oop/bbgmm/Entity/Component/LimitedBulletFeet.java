package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Control.WallChecker;
import it.unibo.oop.bbgmm.Control.WallCheckerImpl;
import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

/**
 * Component used to destroy the Bullet after a number of steps
 */
public class LimitedBulletFeet extends Feet{

    private int steps;
    private Weapon weapon;
    //private final WallChecker wallChecker = new WallCheckerImpl();

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

    @Override
    public void update(double delta) {
        //if(!wallChecker.willCollide(getOwner().get().getBody().getPosition(),getOwner().get().getBody().getDimension())){
        //    move(getOwner().get().getBody().getDirection(),getSpeed());
        //}
    }
}
