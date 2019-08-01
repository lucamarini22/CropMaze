package it.unibo.oop.bbgmm.Boundary;

public interface StatusBar {
    /**
     * Set the max health
     * @param maxLifePoints
     */
    void setMaxLifePoints(int maxLifePoints);

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
