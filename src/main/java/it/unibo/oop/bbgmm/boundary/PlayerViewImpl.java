package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public final class PlayerViewImpl extends AbstractAliveEntityView implements PlayerView {
    private static final int WIDTH = 153, HEIGHT = 216;
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_FRAMES = 6;
    private final StatusBar statusBar;

    public  PlayerViewImpl(final Group group,final StatusBar statusBar){
        super(group, new Dimension2D(WIDTH,HEIGHT));
        this.statusBar=statusBar;
        putAnimation(PossibleEntityState.STABLE,
                staticAnimation(new Image("images/farmerStanding.png")));
        putAnimation(PossibleEntityState.WALKING,
                dynamicAnimation(new Image("images/farmerWalking.png"), Duration.millis(ANIMATION_DURATION),ANIMATION_FRAMES));
        startAnimation(getCurrentState());
    }

    @Override
    public void changeState(final PossibleEntityState state){
        super.changeState(state);
    }

    @Override
    public void setPosition(final Point2D position){
        super.setPosition(position);
        getView().getParent()
                .setTranslateX(-getView().getTranslateX() * scaling().getMxx() + getView().getScene().getWidth() / 2);
        getView().getParent()
                .setTranslateY(-getView().getTranslateY() * scaling().getMyy() + getView().getScene().getHeight() / 2);
    }

    private Scale scaling() {
        return getGroup().getTransforms().stream().filter(t -> t instanceof Scale).map(t -> (Scale) t)
                .findFirst().orElseGet(() -> new Scale(1, 1));
    }


    @Override
    public void setCurrentLifePoints(int currentLifePoints) {
        this.statusBar.setCurrentLifePoints(currentLifePoints);
    }

    @Override
    public void setCoins(int coins) {
        this.statusBar.setCoins(coins);
    }
}
