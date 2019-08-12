package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Alien View.
 */
public class AlienView extends AbstractAliveEntityView {


    private static final int WIDTH = 31, HEIGHT = 72;

    //private static final Image IMG_HURT = new Image("");
    private static final double FRAME_DURATION = 1000;

    /**
     * Constructor for AlienView.
     * @param group
     *          the related group.
     */
    public AlienView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        putAnimation(PossibleEntityState.STABLE, staticAnimation(new Image("images/alienStanding.png")));
        putAnimation(PossibleEntityState.WALKING, dynamicAnimation(new Image("images/alienWalking.png"), Duration.millis(FRAME_DURATION), 11));
        putAnimation(PossibleEntityState.DYING, staticAnimation(new Image("images/alienDying.png")));

        changeState(PossibleEntityState.STABLE);

    }
}
