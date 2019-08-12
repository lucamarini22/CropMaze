package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Class that represent a {@link it.unibo.oop.bbgmm.entity.Coin} view.
 */
public class CoinView extends AbstractEntityView implements LifelessEntityView {
    private static final String COIN_IMAGE = "/images/coinSilver.png";
    private static final int WIDTH = 72;
    private static final int HEIGHT = 97;
    private final AudioPlayer audioPlayer;

    /**
     * {@link CoinView} constructor.
     * @param group
     *      {@link Group} instance in which {@link CoinView} is inserted
     * @param audioPlayer
     *      {@link AudioPlayer} instance
     */
    public CoinView(final Group group, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        getView().setImage(new Image(COIN_IMAGE));
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void playDeathSound() {
        this.audioPlayer.playSound(Music.BULLET_SHOT.getPath());
    }
}
