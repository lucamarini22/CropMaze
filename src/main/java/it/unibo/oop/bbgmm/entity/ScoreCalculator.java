package it.unibo.oop.bbgmm.entity;

/**
 * Calculates the score.
 */
public interface ScoreCalculator {

    /**
     * Gets the calculated score.
     *
     * @param playerStatistics
     *      stores the {@link Player} statistics, like the money collected and the killed enemies
     * @return the score
     */
    int getScore(PlayerStatistics playerStatistics);
}
