package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.*;
import it.unibo.oop.bbgmm.Entity.GameStatisticsImpl;
import it.unibo.oop.bbgmm.Entity.ScoreList;
import it.unibo.oop.bbgmm.Entity.ScoreListImpl;
import it.unibo.oop.bbgmm.Utilities.Pair;
import it.unibo.oop.bbgmm.Utilities.Volume;
import it.unibo.oop.bbgmm.Utilities.VolumeData;
import it.unibo.oop.bbgmm.Utilities.VolumeDataImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * {@link PrincipalController} implementation.
 */
public final class PrincipalControllerImpl implements PrincipalController {

    private VolumeDataImpl volumeData;
    private final PrincipalView view;
    private ScoreList score;
    private Optional<PlayerInputHandler> playerInputHandler = Optional.empty();
    private Optional<GameController> gameControl = Optional.empty();
    private AudioPlayer audioPlayer;

    /**
     * {@link PrincipalControllerImpl} constructor.
     * @param principalStage
     *      Principal {@link Stage}
     */
    public PrincipalControllerImpl(final Stage principalStage) {
        this.volumeData = new VolumeDataImpl();
        this.audioPlayer = new AudioPlayerImpl(volumeData.getMusicVolume().getValue(),
                volumeData.getEffectsVolume().getValue());
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
    public void updateVolume(Volume musicVolume, Volume effectsVolume) {
        this.volumeData.setMusicVolume(musicVolume);
        this.volumeData.setEffectsVolume(effectsVolume);
        this.audioPlayer.setMusicVolume(this.volumeData.getMusicVolume().getValue());
        this.audioPlayer.setSoundVolume(this.volumeData.getEffectsVolume().getValue());
    }

    @Override
    public VolumeData getVolumeData() {
        return this.volumeData;
    }

    @Override
    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
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
        gameControl = Optional.empty();
        playerInputHandler = Optional.empty();
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
    public void showGameField(final Scene scene) {
        gameControl = Optional.of(new GameControllerImpl(new GameStatisticsImpl(),
                new GameFieldViewImpl(this.audioPlayer, this.playerInputHandler.get()), this));
        scene.setRoot(gameControl.get().getGameFieldView().getGroup());
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
