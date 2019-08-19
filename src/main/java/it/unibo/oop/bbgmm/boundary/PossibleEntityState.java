package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.EntityState;

/**
 * All possible state for the entity view.
 */
public enum PossibleEntityState implements EntityState {
    /**
     * Stable state.
     */
    STABLE,
    /**
     * Walking state.
     */
    WALKING,
    /**
     * Dying state.
     */
    DYING
}
