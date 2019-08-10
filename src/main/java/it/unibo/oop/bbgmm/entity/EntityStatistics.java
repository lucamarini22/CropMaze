package it.unibo.oop.bbgmm.entity;

/**
 * Interface that maintains the statistics of various entities.
 */
public interface EntityStatistics {

    /**
     * @return the Health Points of the {@link Player}
     */
    int getPlayerHealth();

    /**
     * @param currentLevel
     *      The number of the current level
     * @return the Health Points of the enemy, based on the level number
     */
    int getEnemyHealth(int currentLevel);

    /**
     * @return the value of a {@link Coin}
     */
    int getCoinValue();
}
