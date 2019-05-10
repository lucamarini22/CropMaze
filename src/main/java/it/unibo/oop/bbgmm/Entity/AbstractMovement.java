package it.unibo.oop.bbgmm.Entity;

public abstract class AbstractMovement extends AbstractComponent implements Movement {
    private State currentState = State.STABLE;
    private Direction directionMovement = Direction.NOTHING;


    /**
     * Set the new state of the entity
     * @param newState
     */
    protected final void setState(final State newState){
        if(!currentState.equals(newState)){
            currentState = newState;
        }
    }

    protected final Direction getDesiredDirection(){
        return directionMovement;
    }

    protected final void setDesiredDirection(final Direction changeDirection){
        directionMovement = changeDirection;
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public State getState() {
        return currentState;
    }
}
