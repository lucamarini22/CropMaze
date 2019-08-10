package it.unibo.oop.bbgmm.Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent a score.
 */
public final class Score implements Serializable {
    private static final long serialVersionUID = 5044857723462203092L;
    private final String playerName;
    private final int score;

    /**
     * Constructor for Score.
     *
     * @param playerName
     *          The name of the player
     * @param score
     *          The score achieved
     */
    public Score(final String playerName, final int score) {
        this.playerName = playerName;
        this.score = score;
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
        return this.score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return this.score == score1.score
                && this.playerName.equals(score1.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerName, this.score);
    }
}
