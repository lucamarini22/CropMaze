package it.unibo.oop.bbgmm.Entity;
import  it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;
import it.unibo.oop.bbgmm.Entity.Component.ClashComponent;
import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import javafx.geometry.Point2D;


public abstract class AbstractMovement extends ClashComponent implements Movement {
    private State currentState = State.STABLE;
    private Point2D directionMovement = Point2D.ZERO;

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
        return directionMovement;
    }

    /**
     * Set the desired movement vector
     * @param newMovement
     *          the movement
     */
    protected final void setDirectionMovement(final Point2D newMovement){
        this.directionMovement = newMovement;
    }
}
