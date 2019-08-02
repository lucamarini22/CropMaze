package it.unibo.oop.bbgmm.Entity;

/**
 * Stores and sets statistics of an {@link Player} object.
 */
public final class PlayerStatisticsImpl implements PlayerStatistics {

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
    public int getCollectedMoney() {
        return 0;
        //return player.get(Inventory.class).isPresent() ? player.get(Inventory.class).get().getMoney() : 0;
    }

    @Override
    public int getKilledEnemies() {
        return this.killedEnemies;
    }

    //event to increment killedEnemies
}
