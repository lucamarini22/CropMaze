package it.unibo.oop.bbgmm.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent a score.
 */
public final class Score implements Serializable {
    private static final long serialVersionUID = 5044857723462203092L;
    private final String playerName;
    private final int scoreAchieved;

    /**
     * Constructor for Score.
     *
     * @param playerName
     *          The name of the player
     * @param scoreAchieved
     *          The score achieved
     */
    public Score(final String playerName, final int scoreAchieved) {
        this.playerName = playerName;
        this.scoreAchieved = scoreAchieved;
    }

    /**
     * Getter for the player's name.
     *
     * @return string
     *          The name of the player
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Getter for the player's score.
     * @return int
     *          The score achieved
     */
    public int getScore() {
        return this.scoreAchieved;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score1 = (Score) o;
        return this.scoreAchieved == score1.scoreAchieved
                && this.playerName.equals(score1.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerName, this.scoreAchieved);
    }
}
