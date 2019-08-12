package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Alien View.
 */
public class AlienView extends AbstractAliveEntityView {


    private static final int WIDTH = 50, HEIGHT = 72;
    private static final double FRAME_DURATION = 1300;
    private final AudioPlayer audioPlayer;

    /**
     * Constructor for AlienView.
     * @param group
     *      the related group
     * @param audioPlayer
     *      the audioPlayer
     */
    public AlienView(final Group group, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioPlayer = audioPlayer;

        putAnimation(PossibleEntityState.WALKING, dynamicAnimation(new Image("images/alienWalking.png"), Duration.millis(FRAME_DURATION), 11));
        putAnimation(PossibleEntityState.DYING, staticAnimation(new Image("images/alienDying.png")));

        changeState(PossibleEntityState.WALKING);

    }

    @Override
    public void playDeathSound() {
        super.playDeathSound();
        this.audioPlayer.playSound(Music.BULLET_SHOT.getPath());
    }
}
