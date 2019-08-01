package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.util.List;

public interface PrincipalController {

    List<Pair<String, Integer>> getRankingList();

    void InsertNewScore(final String name, final Integer result);

    GameController startGame();

    void stopGame();
}
