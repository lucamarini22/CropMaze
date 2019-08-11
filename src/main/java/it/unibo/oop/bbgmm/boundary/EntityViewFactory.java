package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.Direction;

/**
 * Factory for the views of the entities.
 */
public interface EntityViewFactory {

    /**
     * @return a {@link it.unibo.oop.bbgmm.entity.Player} view
     */
    PlayerView createPlayerView();

    /**
     * @return a {@link it.unibo.oop.bbgmm.entity.Alien} view
     */
    AliveEntityView createAlienView();

    /**
     * @param direction
     *      {@link Direction} of the bullet
     * @return {@link it.unibo.oop.bbgmm.entity.Bullet} view
     */
    BulletView createBulletView(Direction direction);

    /**
     * @return a {@link it.unibo.oop.bbgmm.entity.Coin} view
     */
    LifelessEntityView createCoinView();

    /**
     * @return a Double Speed {@link it.unibo.oop.bbgmm.entity.PowerUp} view
     */
    LifelessEntityView createDoubleSpeedView();

    /**
     * @return a Double Damage {@link it.unibo.oop.bbgmm.entity.PowerUp} view
     */
    LifelessEntityView createDoubleDamageView();

    /**
     * @return a Shield {@link it.unibo.oop.bbgmm.entity.PowerUp} view
     */
    LifelessEntityView createShieldView();
}
