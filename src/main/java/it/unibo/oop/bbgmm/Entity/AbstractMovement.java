package it.unibo.oop.bbgmm.Entity;
import it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;
import javafx.geometry.Point2D;


public abstract class AbstractMovement extends AbstractEntityComponent implements Movement {
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

    //devo vedere ancora come usarlo
    @Override
    public void update(double dt) {

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
    protected final Point2D getPosition(){
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

    /**
     * Get the actual direction
     * @return the direction
     */
    protected final Direction getDirection(){ return direction; }

    /**
     * set the direction
     * @param newDirection
     * @return
     */
    protected final void setDirection(Direction newDirection) {
        this.direction = newDirection;
        getOwner().ifPresent(o -> ((Entity) o).getBody());
    }
}
