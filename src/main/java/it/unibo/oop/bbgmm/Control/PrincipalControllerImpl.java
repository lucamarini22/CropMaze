package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.*;
import it.unibo.oop.bbgmm.Entity.GameStatisticsImpl;
import it.unibo.oop.bbgmm.Entity.ScoreList;
import it.unibo.oop.bbgmm.Entity.ScoreListImpl;
import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.scene.Group;
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
    private Optional<PlayerInputHandler> playerInputHandler = Optional.empty();
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
        return this.score.getRanking();
    }

    @Override
    public void InsertNewScore(final String name, final Integer result) {
        this.score.addScore(new Pair<>(name, result));
    }

    @Override
    public void stopGame() {
        if (this.gameControl.isPresent()) {
            this.gameControl.get().stop();
        }
    }

    @Override
    public void startGame() {
        this.gameControl.get().run();
    }

    @Override
    public void showMainMenu(final ViewFactory viewFactory) {
        //manca quello per settare a zero il livello
        viewFactory.createMainMenu();
    }

    @Override
    public void showRankingView(ViewFactory viewFactory) {
        viewFactory.createRankingView();
    }

    /**
     * Method used by the view to show the settings
     *
     * @param viewFactory
     */
    @Override
    public void showSettings(ViewFactory viewFactory) {
        viewFactory.createSettingsMenu();
    }

    @Override
    public void showGameField(final Group group) {
        this.gameControl = Optional.of(new GameControllerImpl(new GameStatisticsImpl(),
                new GameFieldViewImpl(new AudioPlayerImpl(SOUND_VOLUME, MUSIC_VOLUME), this.playerInputHandler.get()), this));
        group.getChildren().clear();
        group.getChildren().addAll(gameControl.get().getGameFieldView().getGroup().getChildren());
        startGame();
    }


    @Override
    public void showInsertScoreView(ViewFactory viewFactory) {
        viewFactory.createInsertScoreView();
    }

    @Override
    public void showGameOver(final ViewFactory viewFactory) {
        viewFactory.createGameOver();
    }


    @Override
    public void setPlayerInputHandler(PlayerInputHandler playerInputHandler) {
        this.playerInputHandler = Optional.of(playerInputHandler);
    }


}
