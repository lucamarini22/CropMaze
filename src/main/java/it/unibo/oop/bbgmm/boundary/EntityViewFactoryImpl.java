package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.EntityType;
import javafx.scene.Group;

/**
 * Instance of {@link EntityViewFactory}.
 */
public final class EntityViewFactoryImpl implements EntityViewFactory {
    private final Group parent;
    private final AudioPlayer audioPlayer;
    private final StatusBar statusBar;

    /**
     * Constructor of {@link EntityViewFactoryImpl}.
     * @param parent
     *      {@link Group} instance parent
     * @param audioPlayer
     *      {@link AudioPlayer} instance
     * @param statusBar
     *      Bar that visualize the {@link it.unibo.oop.bbgmm.entity.Player}'s status
     */
    public EntityViewFactoryImpl(final Group parent, final AudioPlayer audioPlayer, final StatusBar statusBar) {
        this.statusBar = statusBar;
        this.parent = parent;
        this.audioPlayer = audioPlayer;
    }
    @Override
    public PlayerView createPlayerView() {
        return new PlayerViewImpl(parent, statusBar);
    }

    @Override
    public AliveEntityView createAlienView() {
        return new AlienView(parent, audioPlayer);
    }

    @Override
    public BulletView createBulletView(final Direction direction) {
        return new BulletView(parent, direction, audioPlayer);
    }

    @Override
    public LifelessEntityView createCoinView() {
        return new CoinView(parent, audioPlayer);
    }

    @Override
    public LifelessEntityView createDoubleSpeedView() {
        return new PowerUpView(parent, EntityType.DOUBLE_SPEED, audioPlayer);
    }

    @Override
    public LifelessEntityView createDoubleDamageView() {
        return new PowerUpView(parent, EntityType.DOUBLE_DAMAGE, audioPlayer);
    }

    @Override
    public LifelessEntityView createShieldView() {
        return new PowerUpView(parent, EntityType.SHIELD, audioPlayer);
     }
}
