package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.AudioPlayer;
import it.unibo.oop.bbgmm.boundary.AudioPlayerImpl;
import it.unibo.oop.bbgmm.boundary.ViewFactory;
import it.unibo.oop.bbgmm.boundary.PlayerInputHandler;
import it.unibo.oop.bbgmm.boundary.PrincipalView;
import it.unibo.oop.bbgmm.boundary.GameFieldViewImpl;
import it.unibo.oop.bbgmm.entity.GameStatisticsImpl;
import it.unibo.oop.bbgmm.entity.ScoreList;
import it.unibo.oop.bbgmm.entity.ScoreListImpl;
import it.unibo.oop.bbgmm.utilities.Pair;
import it.unibo.oop.bbgmm.utilities.Volume;
import it.unibo.oop.bbgmm.utilities.VolumeData;
import it.unibo.oop.bbgmm.utilities.VolumeDataImpl;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * {@link PrincipalController} implementation.
 */
public final class PrincipalControllerImpl implements PrincipalController {

    private final Stage primaryStage;
    private final AudioPlayer audioPlayer;
    private PrincipalView view;
    private final VolumeDataImpl volumeData;
    private ScoreList score;
    private Optional<PlayerInputHandler> playerInputHandler = Optional.empty();
    private Optional<GameController> gameControl = Optional.empty();


    /**
     * {@link PrincipalControllerImpl} constructor.
     *
     * @param primaryStage
     *      Principal {@link Stage}
     */
    public PrincipalControllerImpl(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.volumeData = new VolumeDataImpl();
        this.audioPlayer = new AudioPlayerImpl(volumeData.getMusicVolume().getValue(),
                volumeData.getEffectsVolume().getValue());
        this.view = new PrincipalView(primaryStage, this);
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
    public void insertNewScore(final String name) {
        this.gameControl.ifPresent(controller -> this.score.addScore(new Pair<>(name, controller.calculateScore())));
    }

    @Override
    public void updateVolume(final Volume musicVolume, final Volume effectsVolume) {
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
        this.gameControl.ifPresent(g -> g.stop());
    }

    @Override
    public void startGame() {
        this.gameControl.ifPresent(g -> g.run());
    }

    @Override
    public void resetGame() {
        stopGame();
        this.gameControl = Optional.empty();
        this.playerInputHandler = Optional.empty();

        this.view = new PrincipalView(primaryStage, this);
    }

    @Override
    public void showMainMenu(final ViewFactory viewFactory) {
        viewFactory.createMainMenu();
    }

    @Override
    public void showRankingView(final ViewFactory viewFactory) {
        viewFactory.createRankingView();
    }

    @Override
    public void showSettings(final ViewFactory viewFactory) {
        viewFactory.createSettingsMenu();
    }

    @Override
    public void showGameField(final Scene scene) {
       this.gameControl = Optional.of(new GameControllerImpl(new GameStatisticsImpl(),
                new GameFieldViewImpl(this.audioPlayer, this.playerInputHandler.get(), this, this.primaryStage),
               this.primaryStage, this));
        scene.setRoot(this.gameControl.get().getGameFieldView().getGroup());
        startGame();
    }

    @Override
    public void showGameOver(final ViewFactory viewFactory) {
        this.playerInputHandler.ifPresent(PlayerInputHandler::reset);
        viewFactory.createGameOver();
    }

    @Override
    public void setPlayerInputHandler(final PlayerInputHandler playerInputHandler) {
        this.playerInputHandler = Optional.of(playerInputHandler);
    }

    @Override
    public Optional<GameController> getGameController() {
        return this.gameControl;
    }

    @Override
    public PrincipalView getView() {
        return this.view;
    }

}
