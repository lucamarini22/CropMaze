package it.unibo.oop.bbgmm.Entity;
import  it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;
import it.unibo.oop.bbgmm.Entity.Component.ClashComponent;
import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import javafx.geometry.Point2D;


public abstract class AbstractMovement extends ClashComponent implements Movement {
    private State currentState = State.STABLE;
    private Point2D desiredPosition = Point2D.ZERO;
    private Direction direction = Direction.NOTHING;

    @Override
    public State getState() {
        return currentState;
    }

    /**
     * Applies movement
     * @param dt
     */

    //non sono sicura su come debba essere utilizzata,
    // il metodo update è riferito a ClashComponent
    @Override
    public void update(double dt) {
        super.update(dt);

    }

    /**
     * Set the new State
     * @param newState
     */
    protected final void setState(final State newState){
        if(!currentState.equals(newState)){
            currentState = newState;
        }
    }

    /**
     *
     * @return the desired movement vector
     */
    protected final Point2D getDirectionMovement(){
        return desiredPosition;
    }

    /**
     * Set the new movement vector
     * @param newMovement
     *              new movement to do
     *
     */
    protected final void setPosition(final Point2D newMovement){

        this.desiredPosition = newMovement;
    }
}
