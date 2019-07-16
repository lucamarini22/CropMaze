package it.unibo.oop.bbgmm.Entity;

/**
 * It stores the data to calculate the final score.
 */
public interface GameStatistics {
    /**
     * @return the last level completed
     */
    int getCurrentLevel();

    /**
     * @return the final score
     */
    int getScore();

    /**
     * Change the current level.
     *
     * @param currentLevel
     *      the number of the current level
     */
    void setCurrentLevel(int currentLevel);

    /**
     * Change the score.
     *
     * @param score
     *     score to set
     */
    void setScore(int score);

    /**
     * Reset the score and the current level.
     */
    void resetStatistics();

}
