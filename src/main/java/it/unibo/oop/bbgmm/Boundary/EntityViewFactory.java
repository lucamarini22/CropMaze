package it.unibo.oop.bbgmm.Boundary;

/**
 * Factory for the views of the entities.
 */
public interface EntityViewFactory {

    /**
     * @return a {@link it.unibo.oop.bbgmm.Entity.Player} view
     */
    PlayerView createPlayerView();

    /**
     * @return a {@link it.unibo.oop.bbgmm.Entity.Alien} view
     */
    AliveEntityView createAlienView();

    /**
     * @return a {@link it.unibo.oop.bbgmm.Entity.Coin} view
     */
    LifelessEntityView createCoinView();

    LifelessEntityView createDoubleSpeedView();

    LifelessEntityView createDoubleDamageView();

    LifelessEntityView createShieldView();

    //upgrades and power ups
}
