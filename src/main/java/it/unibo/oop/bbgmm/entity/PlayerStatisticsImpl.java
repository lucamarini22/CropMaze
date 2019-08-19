package it.unibo.oop.bbgmm.entity;

/**
 * Stores and sets statistics of an {@link Player} object.
 */
public final class PlayerStatisticsImpl implements PlayerStatistics {

    private static final int INCREASE = 1;
    private int collectedMoney;
    private int killedEnemies;

    /**
     * Constructor for the player's statistics.
     */
    public PlayerStatisticsImpl() {
        this.killedEnemies = 0;
        this.collectedMoney = 0;
    }

    @Override
    public void increaseCollectedMoney() {
        this.collectedMoney += INCREASE;
    }

    @Override
    public void increaseKilledEnemies() {
        this.killedEnemies += INCREASE;
    }

    @Override
    public int getCollectedMoney() {
        return this.collectedMoney;
    }

    @Override
    public int getKilledEnemies() {
        return this.killedEnemies;
    }
}
