package it.unibo.oop.bbgmm.Boundary;


import it.unibo.oop.bbgmm.Entity.EntityState;

/**
 * Interface for the entities that can change state during the game
 * @param <S>
 *      The EntityState type
 */
public interface EntityChangeState<S extends EntityState> {
    /**
     * Runs the state animation
     * @param state
     *      The state
     */
    void changeState(S state);
}
