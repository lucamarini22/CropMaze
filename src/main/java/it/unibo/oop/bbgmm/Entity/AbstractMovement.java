package it.unibo.oop.bbgmm.Entity;
import  it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;
import javafx.geometry.Point2D;


public abstract class AbstractMovement extends AbstractEntityComponent implements Movement {
    private State currentState = State.STABLE;
    private Point2D directionMovement = Point2D.ZERO;
    private double speedMovement;

    public AbstractMovement(final double speedMovement) {
        this.speedMovement = speedMovement;
    }

    /**
     * Set the new state of the entity
     * @param newState
     */
    protected final void setState(final State newState){
        if(!currentState.equals(newState)){
            currentState = newState;
        }
    }

    protected final Point2D getDesiredDirection(){
        return directionMovement;
    }

    protected final void setDesiredDirection(Point2D changeDirection){
        directionMovement = changeDirection;

    }

    @Override
    public void move(Point2D direction) {
            this.directionMovement = direction;
            //deve cambiare la posizione del body
    }

    @Override
    public double getSpeed() {
        return this.speedMovement;
    }

    @Override
    public void setSpeed(double speed) {
        this.speedMovement = speed;
    }

    @Override
    public State getState() {
        return currentState;
    }
}
