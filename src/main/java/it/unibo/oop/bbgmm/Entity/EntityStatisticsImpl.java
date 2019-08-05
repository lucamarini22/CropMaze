package it.unibo.oop.bbgmm.Entity;

/**
 * Implementation of {@link EntityStatistics}.
 */
public final class EntityStatisticsImpl implements EntityStatistics {

    private static final int COIN_VALUE = 10;
    private static final int PLAYER_HEALTH = 1000;
    private static final int ENEMY_HEALTH = 10;

    @Override
    public int getPlayerHealth() {
        return PLAYER_HEALTH;
    }

    @Override
    public int getEnemyHealth(final int currentLevel) {
        return ENEMY_HEALTH * currentLevel;
    }

    @Override
    public int getCoinValue() {
        return COIN_VALUE;
    }
}
