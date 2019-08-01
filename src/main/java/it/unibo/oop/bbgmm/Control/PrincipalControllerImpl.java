package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.AudioPlayerImpl;
import it.unibo.oop.bbgmm.Boundary.GameFieldViewImpl;
import it.unibo.oop.bbgmm.Boundary.PrincipalView;
import it.unibo.oop.bbgmm.Entity.GameStatisticsImpl;
import it.unibo.oop.bbgmm.Entity.ScoreList;
import it.unibo.oop.bbgmm.Entity.ScoreListImpl;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * {@link PrincipalController} implementation.
 */
public final class PrincipalControllerImpl implements PrincipalController {

    //class VOLUME to implement?
    private static final double SOUND_VOLUME = 10;
    private static final double MUSIC_VOLUME = 10;
    private final PrincipalView view;
    private ScoreList score;
    private Optional<GameController> gameControl = Optional.empty();

    /**
     * {@link PrincipalControllerImpl} constructor.
     * @param principalStage
     *      Principal {@link Stage}
     */
    public PrincipalControllerImpl(final Stage principalStage) {
        this.view = new PrincipalView(principalStage, this);
        try {
            this.score = new ScoreListImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Pair<String, Integer>> getRankingList() {
        return score.getRanking();
    }

    @Override
    public void InsertNewScore(final String name, final Integer result) {
        score.addScore(new Pair<>(name, result));
    }

    @Override
    public GameController startGame() {
        gameControl = Optional.of(new GameControllerImpl(new GameStatisticsImpl(),
                new GameFieldViewImpl(new AudioPlayerImpl(SOUND_VOLUME, MUSIC_VOLUME)), this));
        return gameControl.get();
    }

    @Override
    public void stopGame() {
        if (gameControl.isPresent()) {
            gameControl.get().stop();
        }
    }
}
