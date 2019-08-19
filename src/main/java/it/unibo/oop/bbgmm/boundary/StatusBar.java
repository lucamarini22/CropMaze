package it.unibo.oop.bbgmm.boundary;

/**
 * Status bar interface.
 */
public interface StatusBar {

    /**
     * Set current health.
     * @param currentLifePoints
     *      The new amount of life points.
     */
    void setCurrentLifePoints(int currentLifePoints);

    /**
     * Set the coins collected.
     * @param coins
     *      Number of coins collected.
     */
    void setCoins(int coins);


}
