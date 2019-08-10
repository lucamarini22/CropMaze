package it.unibo.oop.bbgmm.boundary;

public interface StatusBar {

    /**
     * Set current health
     * @param currentLifePoints
     */
    void setCurrentLifePoints(int currentLifePoints);

    /**
     * Set the coins collected
     * @param coins
     *      Number of coins collected
     */
    void setCoins(int coins);


}
