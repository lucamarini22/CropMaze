package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.Group;

/**
 * Instance of {@link EntityViewFactory}.
 */
public final class EntityViewFactoryImpl implements EntityViewFactory {
    private final Group parent;
    private final AudioPlayer audioPlayer;

    /**
     * Constructor of {@link EntityViewFactoryImpl}.
     * @param parent
     *      {@link Group} instance parent
     * @param audioPlayer
     *      {@link AudioPlayer} instance
     */
    public EntityViewFactoryImpl(final Group parent, final AudioPlayer audioPlayer) {
        this.parent = parent;
        this.audioPlayer = audioPlayer;
    }
    @Override
    public PlayerView createPlayerView() {
        return new PlayerViewImpl(parent, );
    }

    @Override
    public AliveEntityView createAlienView() {
        return new AlienView(parent);
    }

    @Override
    public LifelessEntityView createCoinView() {
        return new CoinView(parent, audioPlayer);
    }
}
