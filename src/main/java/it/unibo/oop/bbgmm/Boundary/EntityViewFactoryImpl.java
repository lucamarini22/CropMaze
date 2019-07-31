package it.unibo.oop.bbgmm.Boundary;

/**
 * Instance of {@link EntityViewFactory}.
 */
public final class EntityViewFactoryImpl implements EntityViewFactory {
    @Override
    public PlayerView createPlayerView() {
        //return new PlayerViewImpl();
        //change
        return null;
    }

    @Override
    public AliveEntityView createAlienView() {
        //
        return null;
    }

    @Override
    public LifelessEntityView createCoinView() {
        //
        return null;
    }
}
