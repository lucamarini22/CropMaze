package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Movement;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * permite to follow a determinate entity and change the desired direciton
 */
public class BrainComponent extends AbstractEntityComponent implements Brain {

    private Point2D nextPosition = Point2D.ZERO;


    /**
     * next
     */
    public BrainComponent() {
        super();
    }


    @Override
    public void update(double delta) {
        randomMovement();
        super.update(delta);
    }


    @Override
    public void randomMovement(){
        int min = -1;
        int max = 1;
        getOwner().get().get(Movement.class).ifPresent(movement -> {
            double xValue = min + Math.random()*(max-min);
            double yValue = min + Math.random()*(max-min);
            this.nextPosition = new Point2D(xValue, yValue);
        movement.move(this.nextPosition);
        });
    }
}
