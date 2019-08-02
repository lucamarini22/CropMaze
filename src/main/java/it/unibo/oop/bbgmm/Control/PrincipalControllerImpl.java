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
        this.view = new PrincipalView(principalStage, this, audioPlayer);
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
    public void stopGame() {
        if (gameControl.isPresent()) {
            gameControl.get().stop();
        }
    }

    @Override
    public void startGame() {
        gameControl.get().run();
    }

    @Override
    public void showMainMenu(final ViewFactory viewFactory) {
        //manca quello per settare a zero il livello
        viewFactory.createMainMenu();
    }

    @Override
    public void showGameField(final Group group) {
        gameControl = Optional.of(new GameControllerImpl(new GameStatisticsImpl(),
                new GameFieldViewImpl(new AudioPlayerImpl(volumeData.getMusicVolume().getValue(),
                        volumeData.getEffectsVolume().getValue())), this));
        group.getChildren().clear();
        group.getChildren().addAll(gameControl.get().getGameFieldView().getGroup().getChildren());
        startGame();
    }

    @Override
    public void showGameOver(final ViewFactory viewFactory) {
        viewFactory.createGameOver();
    }


}
