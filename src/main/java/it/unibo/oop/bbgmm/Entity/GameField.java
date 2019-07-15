package it.unibo.oop.bbgmm.Entity;

/**
 * Represent the game field.
 */
public interface GameField {

    /**
     * Update the field simulation.
     *
     * @param up
     *            Time to simulate, in seconds.
     */
    void update(double up);
}
