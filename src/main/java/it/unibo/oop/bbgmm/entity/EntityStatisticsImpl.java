package it.unibo.oop.bbgmm.entity;

/**
 * Implementation of {@link EntityStatistics}.
 */
public final class EntityStatisticsImpl implements EntityStatistics {

    private static final int PLAYER_HEALTH = 10000;
    private static final int ENEMY_HEALTH = 10;

    @Override
    public int getPlayerHealth() {
        return PLAYER_HEALTH;
    }

    @Override
    public int getEnemyHealth(final int currentLevel) {
        return ENEMY_HEALTH * currentLevel;
    }
}
