package it.unibo.oop.bbgmm.entity;

/**
 * Implementation of GameStatistics.
 */
public final class GameStatisticsImpl implements GameStatistics {

    private static final int FIRST_LEVEL = 1;
    private int currentLevel;

    /**
     * {@link GameStatisticsImpl} constructor.
     */
    public GameStatisticsImpl() {
        this.currentLevel = FIRST_LEVEL;
    }

    @Override
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void setCurrentLevel(final int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
