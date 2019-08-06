package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.AudioPlayer;
import it.unibo.oop.bbgmm.Boundary.PlayerInputHandler;
import it.unibo.oop.bbgmm.Boundary.ViewFactory;
import it.unibo.oop.bbgmm.Utilities.Pair;
import it.unibo.oop.bbgmm.Utilities.Volume;
import it.unibo.oop.bbgmm.Utilities.VolumeData;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

/**
 * Main controller of the game
 */
public interface PrincipalController {

    /**
     * Getter for the ranking list
     * @return List<Pair<String, Integer>>
     */
    List<Pair<String, Integer>> getRankingList();

    /**
     * Method used to insert in the ranking a new score
     * @param name
     * @param result
     */
    void InsertNewScore(final String name, final Integer result);

    /**
     * Method used to update the audioPlayer
     * @param musicVolume
     * @param effectsVolume
     */
    void updateVolume(final Volume musicVolume, final Volume effectsVolume);

    /**
     * Getter for the Volume
     * @return VolumeData
     */
    VolumeData getVolumeData();

    /**
     *Getter for the AudioPlayer
     * @return AudioPlayer
     */
    AudioPlayer getAudioPlayer();

    /**
     * Method that stops the game
     */
    void stopGame();

    /**
     * Method that starts the game
     */
    void startGame();

    /**
     * Method that reset the game
     */
    void resetGame();

    /**
     * Method used by the view to show the main menu
     * @param viewFactory
     */
    void showMainMenu(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the ranking
     * @param viewFactory
     */
    void showRankingView(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the settings
     * @param viewFactory
     */
    void showSettings(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the GameField and start the game
     * @param scene
     */
    void showGameField(final Scene scene);

    /**
     * Method used by the view to show the insertScore screen
     * @param viewFactory
     */
    void showInsertScoreView(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the gameOver screen
     * @param viewFactory
     */
    void showGameOver(final ViewFactory viewFactory);

    /**
     * Method invoked by the view to set the PlayerInputListener
     * @param playerInputHandler
     */
    void setPlayerInputHandler(final PlayerInputHandler playerInputHandler);

    /**
     * Getter for the game controller
     * @return GameController
     */
    Optional<GameController> getGameController();
}
