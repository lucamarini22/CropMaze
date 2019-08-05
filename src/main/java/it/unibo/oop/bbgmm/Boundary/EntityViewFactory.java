package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.Direction;

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
     * @param direction
     *      {@link Direction} of the bullet
     * @return {@link it.unibo.oop.bbgmm.Entity.Bullet} view
     */
    BulletView createBulletView(Direction direction);

    /**
     * @return a {@link it.unibo.oop.bbgmm.Entity.Coin} view
     */
    LifelessEntityView createCoinView();

    /**
     * @return a Double Speed {@link it.unibo.oop.bbgmm.Entity.PowerUp} view
     */
    LifelessEntityView createDoubleSpeedView();

    /**
     * @return a Double Damage {@link it.unibo.oop.bbgmm.Entity.PowerUp} view
     */
    LifelessEntityView createDoubleDamageView();

    /**
     * @return a Shield {@link it.unibo.oop.bbgmm.Entity.PowerUp} view
     */
    LifelessEntityView createShieldView();
}
