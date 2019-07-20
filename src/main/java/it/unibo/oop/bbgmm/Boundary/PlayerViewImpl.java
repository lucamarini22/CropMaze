package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.EntityState;
import it.unibo.oop.bbgmm.Entity.Movement;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerViewImpl extends EntityChangeStateView<PossibleEntityState> implements PlayerView {
    private static final int ANIMATION_DURATION = 0;
    private static final int ANIMATION_FRAMES = 0;

    public  PlayerViewImpl(final Group group, final Dimension2D dimension){
        super(group, dimension);
        putAnimation(PossibleEntityState.STABLE,
                staticAnimation(new Image("")));
        putAnimation(PossibleEntityState.WALKING,
                dinamicAnimation(new Image(""), Duration.millis(ANIMATION_DURATION),ANIMATION_FRAMES));
    }

    @Override
    public void changeState(final PossibleEntityState state){
        super.changeState(state);
    }

    @Override
    public void setPosition(final Point2D position){
        super.setPosition(position);
    }
}
