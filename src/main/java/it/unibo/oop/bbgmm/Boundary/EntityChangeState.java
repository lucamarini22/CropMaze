package it.unibo.oop.bbgmm.Boundary;


import it.unibo.oop.bbgmm.Entity.EntityState;

public interface EntityChangeState {
    /**
     * Runs the state animation
     * @param state
     *      The state
     */
    void changeState(EntityState state);
}
