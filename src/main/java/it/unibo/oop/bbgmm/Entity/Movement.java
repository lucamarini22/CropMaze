package it.unibo.oop.bbgmm.Entity;

/**
 * for manage movement of the entity
 */
public interface Movement extends EntityComponent {

    /**
     *
     * @param direction
     *          the direction where the entity will move
     */
    void move(Direction direction);

    /**
     *
     * @return Movement state
     */
    State getState();



    enum State implements EntityState{
        STABLE, WALKING, SHOOTING, DYING;
    }

    enum Direction{
        NORTH, SOUTH, EAST, WEST, NOTHING;
    }


}
