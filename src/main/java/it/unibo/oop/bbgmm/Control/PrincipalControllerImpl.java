package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.PrincipalView;
import it.unibo.oop.bbgmm.Entity.ScoreList;
import it.unibo.oop.bbgmm.Entity.ScoreListImpl;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PrincipalControllerImpl implements PrincipalController {

    private final PrincipalView view;
    private ScoreList score;

    public PrincipalControllerImpl(final Stage principalStage) {
        this.view = new PrincipalView(principalStage, this);
        try {
            this.score = new ScoreListImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pair<String, Integer>> getRankingList(){
        return score.getRanking();
    }
}
