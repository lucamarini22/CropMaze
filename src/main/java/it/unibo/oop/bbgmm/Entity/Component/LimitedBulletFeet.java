package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Control.WallChecker;
import it.unibo.oop.bbgmm.Control.WallCheckerImpl;
import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

/**
 * Component used to destroy the Bullet after a number of steps
 */
public class LimitedBulletFeet extends Feet{

    private static final int STEP = 1;
    private Life lifeComponent;
    private Weapon weapon;
    //private final WallChecker wallChecker = new WallCheckerImpl();

    /**
     * @param walkingSpeed entity speed for the movement
     */
    public LimitedBulletFeet(final Weapon weapon, final double walkingSpeed, final Life lifeComponent) {
        super(walkingSpeed);
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
    }

    @Override
    public void move(Direction direction, double speed) {
        if(lifeComponent.isAlive()){
            super.move(direction, speed);
        }
        else{
            remove();
        }
        lifeComponent.damaged(STEP);
    }

    @Override
    public void update(double delta) {
        /*if(!wallChecker.willCollide(getOwner().get().getBody().getPosition(),getOwner().get().getBody().getDimension())){
            move(getOwner().get().getBody().getDirection(),getSpeed());
        }
        else{
            remove();
        }*/
    }

    private void remove(){
        weapon.removeBullet((Bullet)getOwner().get());
        getOwner().get().destroy();
    }
}
