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
    @Override
    public void update(double dt) {
        super.update(dt);
        final Point2D movVector = movementToCompute(dt);
        if(!movVector.equals(Point2D.ZERO)){
            applyMovement(getOwner().get().getBody(), movVector);
        }
    }

    /**
     * Return the movement vector to apply to the body
     * @param dt
     *          delta time seconds since last call
     * @return
     *         movement vector to be applied
     */
    protected abstract Point2D movementToCompute(double dt);

    /**
     * Apply force to the entity's body to move it
     * @param body
     *           The owner body
     * @param movement
     *           Movement vector
     */
    protected void applyMovement(final EntityBody body, final Point2D movement){
        body.applyImpulse(movement);
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
