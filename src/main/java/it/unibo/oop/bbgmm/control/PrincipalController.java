package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.AudioPlayer;
import it.unibo.oop.bbgmm.boundary.ViewFactory;
import it.unibo.oop.bbgmm.boundary.PlayerInputHandler;
import it.unibo.oop.bbgmm.boundary.PrincipalView;
import it.unibo.oop.bbgmm.utilities.Pair;
import it.unibo.oop.bbgmm.utilities.Volume;
import it.unibo.oop.bbgmm.utilities.VolumeData;
import javafx.scene.Scene;

import java.util.List;
import java.util.Optional;

/**
 * Main controller of the game.
 */
public interface PrincipalController {

    /**
     * Getter for the ranking list.
     *
     * @return List<Pair<String, Integer>>
     *          The list of ranking
     */
    List<Pair<String, Integer>> getRankingList();

    /**
     * Method used to insert in the ranking a new score.
     *
     * @param name
     *          The name of the player
     */
    void insertNewScore(String name);

    /**
     * Method used to update the audioPlayer.
     *
     * @param musicVolume
     *          The volume of the music
     * @param effectsVolume
     *          The volume of the effects
     */
    void updateVolume(Volume musicVolume, Volume effectsVolume);

    /**
     * Getter for the Volume.
     *
     * @return VolumeData
     *          The volume
     */
    VolumeData getVolumeData();

    /**
     *Getter for the AudioPlayer.
     *
     * @return AudioPlayer
     *          The audioPlayer
     */
    AudioPlayer getAudioPlayer();

    /**
     * Method that stops the game.
     */
    void stopGame();

    /**
     * Method that starts the game.
     */
    void startGame();

    /**
     * Method that resets the game.
     */
    void resetGame();

    /**
     * Method used by the view to show the mainMenu.
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showMainMenu(ViewFactory viewFactory);

    /**
     * Method used by the view to show the ranking.
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showRankingView(ViewFactory viewFactory);

    /**
     * Method used by the view to show the settings.
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showSettings(ViewFactory viewFactory);

    /**
     * Method used by the view to show the GameField and start the game.
     *
     * @param scene
     *          The scene displayed in the stage
     */
    void showGameField(Scene scene);

    /**
     * Method used by the view to show the gameOver screen.
     *
     * @param viewFactory
     *          The factory for the views
     */
    void showGameOver(ViewFactory viewFactory);

    /**
     * Method invoked by the view to set the PlayerInputListener.
     *
     * @param playerInputHandler
     *          The handler for the player moves
     */
    void setPlayerInputHandler(PlayerInputHandler playerInputHandler);

    /**
     * Getter for the game controller.
     *
     * @return GameController
     *          The game controller
     */
    Optional<GameController> getGameController();

    /**
     * @return the {@link PrincipalView}
     */
    PrincipalView getView();
}
