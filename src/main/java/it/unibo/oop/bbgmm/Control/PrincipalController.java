package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.AudioPlayer;
import it.unibo.oop.bbgmm.Boundary.EndLevelView;
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
     *
     * @return List<Pair<String, Integer>>
     *          The list of ranking
     */
    List<Pair<String, Integer>> getRankingList();

    /**
     * Method used to insert in the ranking a new score
     *
     * @param name
     *          The name of the player
     */
    void insertNewScore(final String name);

    /**
     * Method used to update the audioPlayer
     *
     * @param musicVolume
     *          The volume of the music
     * @param effectsVolume
     *          The volume of the effects
     */
    void updateVolume(final Volume musicVolume, final Volume effectsVolume);

    /**
     * Getter for the Volume
     *
     * @return VolumeData
     *          The volume
     */
    VolumeData getVolumeData();

    /**
     *Getter for the AudioPlayer
     *
     * @return AudioPlayer
     *          The audioPlayer
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
     * Method that resets the game
     */
    void resetGame();

    /**
     * Method used by the view to show the mainMenu
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showMainMenu(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the ranking
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showRankingView(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the settings
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showSettings(final ViewFactory viewFactory);

    /**
     * Method used by the view to show the GameField and start the game
     *
     * @param scene
     *          The scene displayed in the stage
     */
    void showGameField(final Scene scene);

    /**
     * Method used by the view to show the gameOver screen
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showGameOver(final ViewFactory viewFactory);

    /**
     * Method invoked by the view to set the PlayerInputListener
     *
     * @param playerInputHandler
     *          The handler for the player moves
     */
    void setPlayerInputHandler(final PlayerInputHandler playerInputHandler);

    /**
     * Getter for the game controller
     *
     * @return GameController
     *          The game controller
     */
    Optional<GameController> getGameController();
}
