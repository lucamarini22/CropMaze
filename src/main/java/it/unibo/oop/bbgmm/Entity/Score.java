package it.unibo.oop.bbgmm.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Score implements Serializable {
    private static final long serialVersionUID = 5044857723462203092L;
    private final String playerName;
    private final int level;

    public Score(String playerName, int level) {
        this.playerName=playerName;
        this.level=level;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return level == score.level &&
                playerName.equals(score.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, level);
    }
}
