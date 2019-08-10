package it.unibo.oop.bbgmm.entity;

/**
 * Calculates the score using the Player statistics.
 */
public final class BasicScoreCalculator implements ScoreCalculator {
    private static final int KILL_MULTIPLIER = 50;
    private static final int MONEY_MULTIPLIER = 1;
    @Override
    public int getScore(final PlayerStatistics playerStatistics) {
        return playerStatistics.getCollectedMoney() * MONEY_MULTIPLIER
                + playerStatistics.getKilledEnemies() * KILL_MULTIPLIER;
    }
}
