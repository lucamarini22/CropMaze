package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Class that represent a {@link it.unibo.oop.bbgmm.Entity.Coin} view.
 */
public class CoinView extends EntityViewImpl implements LifelessEntityView {

    private static final String COIN_IMAGE = ""; //modify
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private final AudioPlayer audioplayer;

    /**
     * {@link CoinView} constructor.
     * @param group
     *      {@link Group} instance in which {@link CoinView} is inserted
     * @param audioPlayer
     *      {@link AudioPlayer} instance
     */
    public CoinView(final Group group, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioplayer = audioPlayer;
        getView().setImage(new Image(COIN_IMAGE));
    }
    //remove?
}