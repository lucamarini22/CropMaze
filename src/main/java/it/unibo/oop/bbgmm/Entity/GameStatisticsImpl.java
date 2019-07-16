package it.unibo.oop.bbgmm.Entity;

public class GameStatisticsImpl implements GameStatistics {

    private static final int FIRST_LEVEL = 0;
    private static final int ZERO_POINTS = 0;

    private int currentLevel;
    private int score;

    GameStatisticsImpl() {
        this.currentLevel = FIRST_LEVEL;
        this.score = ZERO_POINTS;
    }

    @Override
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setCurrentLevel(final int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void setScore(final int score) {
        this.score = score;
    }

    @Override
    public void resetStatistics() {
        this.setScore(ZERO_POINTS);
        this.setCurrentLevel(FIRST_LEVEL);
    }
}
