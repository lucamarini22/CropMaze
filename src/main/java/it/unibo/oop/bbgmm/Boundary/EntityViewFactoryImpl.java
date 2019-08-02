package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.PowerTag;
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
        StatusBar statusBar = new StatusBarImpl();
        return new PlayerViewImpl(parent, statusBar);
    }

    @Override
    public AliveEntityView createAlienView() {
        return new AlienView(parent);
    }

    @Override
    public LifelessEntityView createCoinView() {
        return new CoinView(parent, audioPlayer);
    }

    @Override
    public LifelessEntityView createDoubleSpeedView() {
        return new PowerUpView(parent, PowerTag.DOUBLESPEED);
    }

    @Override
    public LifelessEntityView createDoubleDamageView() {
        return new PowerUpView(parent, PowerTag.DOUBLEDAMAGE);
    }

    @Override
    public LifelessEntityView createShieldView() {
        return new PowerUpView(parent, PowerTag.SHIELD);
     }
}
