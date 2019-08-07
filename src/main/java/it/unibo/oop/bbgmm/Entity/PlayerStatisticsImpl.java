package it.unibo.oop.bbgmm.Entity;

/**
 * Stores and sets statistics of an {@link Player} object.
 */
public final class PlayerStatisticsImpl implements PlayerStatistics {

    private static final int INCREASE = 1;
    private int collectedMoney;
    private int killedEnemies;
    private final Entity player;

    /**
     * Constructor for the player's statistics.
     *
     * @param player
     *      The {@link Player} tracked
     */
    public PlayerStatisticsImpl(final Entity player) {
        this.player = player;
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

    //event to increment killedEnemies
}
