package it.unibo.oop.bbgmm.Entity;

/**
 * Stores the {@link Player} statistics.
 */
public interface PlayerStatistics {

    /**
     * Increases the number of collected money.
     */
    void increaseCollectedMoney();

    /**
     * Increases the number of killed enemies.
     */
    void increaseKilledEnemies();

    /**
     * @return amount of collected money, that is the value of the all collected coins
     */
    int getCollectedMoney();

    /**
     * @return amount of killed enemies
     */
    int getKilledEnemies();
}
