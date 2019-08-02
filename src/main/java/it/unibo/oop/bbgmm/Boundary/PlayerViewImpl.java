package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class PlayerViewImpl extends AbstractAliveEntityView implements PlayerView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private static final int ANIMATION_DURATION = 0;
    private static final int ANIMATION_FRAMES = 0;
    private final StatusBar statusBar;

    public  PlayerViewImpl(final Group group,final StatusBar statusBar){
        super(group, new Dimension2D(WIDTH,HEIGHT));
        this.statusBar=statusBar;
        putAnimation(PossibleEntityState.STABLE,
                staticAnimation(new Image("images/p1_stand.png")));
        putAnimation(PossibleEntityState.WALKING,
                dynamicAnimation(new Image("images/p1_walk.png"), Duration.millis(ANIMATION_DURATION),ANIMATION_FRAMES));
        startAnimation(getCurrentState());
    }

    @Override
    public void changeState(final PossibleEntityState state){
        super.changeState(state);
    }

    @Override
    public void setPosition(final Point2D position){
        super.setPosition(position);
    }


    @Override
    public void setMaxLifePoints(int maxLifePoints) {

    }

    @Override
    public void setCurrentLifePoints(int currentLifePoints) {

    }

    @Override
    public void setCoins(int coins) {

    }
}
