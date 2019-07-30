package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AlienView extends AliveEntityViewImpl {


    private static final int WIDTH = 50, HEIGHT = 50;
    //private static final Image IMG_HURT = new Image("");
    private static final double FRAME_DURATION=700;

    public AlienView(Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        putAnimation(PossibleEntityState.STABLE, staticAnimation(new Image("")));
        putAnimation(PossibleEntityState.WALKING, dynamicAnimation(new Image (""), Duration.millis(FRAME_DURATION),2));
        putAnimation(PossibleEntityState.DYING, staticAnimation(new Image ("")));

        changeState(PossibleEntityState.WALKING);

    }
}
