package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.io.IOException;
import java.util.List;

/**
 * Class responsible for the score list.
 */
public interface ScoreList {

    /**
     * Add a new score to the file.
     *
     * @param score
     *          The score to insert
     */
    void addScore(Pair<String, Integer> score);

    /**
     * Getter for the list of the ranking.
     *
     * @return List<Pair<String, Integer>>
     *          List of ranking
     */
    List<Pair<String, Integer>> getRanking();

    /**
     * Clears the ranking list.
     *
     * @throws IOException
     *          Exception if the file does not exist
     */
    void deleteAll() throws IOException;
}
