package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Direction;

/**
 * Component used to destroy the entity after a number of steps
 */
public class LimitedFeet extends Feet{

    private int steps;

    /**
     * @param walkingSpeed entity speed for the movement
     */
    public LimitedFeet(double walkingSpeed, int steps) {
        super(walkingSpeed);
        this.steps = steps;
    }

    @Override
    public void move(Direction direction, double speed) {
        if(steps > 0){
            super.move(direction, speed);
        }
        else{
            //bullet has finished its range so it must be destroyed
        }
        steps--;
    }
}
