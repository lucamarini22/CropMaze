package it.unibo.oop.bbgmm.Entity;

/**
 * Implementation of GameStatistics.
 */
public final class GameStatisticsImpl implements GameStatistics {

    private static final int FIRST_LEVEL = 0;
    private static final int ZERO_POINTS = 0;

    private int currentLevel;
    private int score;

    /**
     * {@link GameStatisticsImpl} constructor.
     */
    public GameStatisticsImpl() {
        this.currentLevel = FIRST_LEVEL;
        this.score = ZERO_POINTS;
    }

    @Override
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void setCurrentLevel(final int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void resetStatistics() {
        this.setCurrentLevel(FIRST_LEVEL);
    }
}
