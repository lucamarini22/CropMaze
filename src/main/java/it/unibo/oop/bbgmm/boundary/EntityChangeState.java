package it.unibo.oop.bbgmm.boundary;


import it.unibo.oop.bbgmm.entity.EntityState;

/**
 * Interface for the entities that can change state during the game
 * @param <S>
 *      The EntityState type
 */
public interface EntityChangeState<S extends EntityState> extends EntityView{
    /**
     * Runs the state animation
     * @param state
     *      The state
     */
    void changeState(S state);
}
