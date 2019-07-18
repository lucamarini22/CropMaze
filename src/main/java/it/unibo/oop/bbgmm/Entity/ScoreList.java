package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.io.IOException;
import java.util.List;

public interface ScoreList {

    void addScore(Pair<String, Integer> score);

    List<Pair<String, Integer>> getRanking();

    void deleteAll() throws IOException;
}
