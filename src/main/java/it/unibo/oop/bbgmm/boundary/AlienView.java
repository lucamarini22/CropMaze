package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AlienView extends AbstractAliveEntityView{


    private static final int WIDTH = 72, HEIGHT = 97;
    //private static final Image IMG_HURT = new Image("");
    private static final double FRAME_DURATION=700;

    public AlienView(Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        putAnimation(PossibleEntityState.STABLE, staticAnimation(new Image("images/slimeGreen.png")));
        putAnimation(PossibleEntityState.WALKING, dynamicAnimation(new Image ("images/slimeGreen_moving.png"), Duration.millis(FRAME_DURATION),2));
        putAnimation(PossibleEntityState.DYING, staticAnimation(new Image ("images/slimeGreen_dead.png")));

        changeState(PossibleEntityState.STABLE);

    }
}
