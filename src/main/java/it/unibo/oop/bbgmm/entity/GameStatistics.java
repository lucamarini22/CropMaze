package it.unibo.oop.bbgmm.entity;

/**
 * It stores the data of the game.
 */
public interface GameStatistics {
    /**
     * @return the last level completed
     */
    int getCurrentLevel();

    /**
     * Change the current level.
     *
     * @param currentLevel
     *      the number of the current level
     */
    void setCurrentLevel(int currentLevel);

    /**
     * Reset the score and the current level.
     */
    void resetStatistics();
}
