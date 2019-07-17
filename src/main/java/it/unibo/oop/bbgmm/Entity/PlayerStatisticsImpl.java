package it.unibo.oop.bbgmm.Entity;

import java.util.Objects;

/**
 * Stores and sets statistics of an {@link Player} object.
 */
public final class PlayerStatisticsImpl implements PlayerStatistics {

    private int collectedMoney;
    private int killedEnemies;
    private final Player player;

    PlayerStatisticsImpl(final Player player) {
        this.player = Objects.requireNonNull(player);
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
