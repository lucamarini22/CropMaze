package it.unibo.oop.bbgmm.Entity;

/**
 * It allows to upgrade the player's statistics
 */
public interface Upgrade {

    enum UpgradeType {
       SPEED, DAMAGE, LIFE, RANGE
    }
    /**
     * verify if can be applied the upgrade
     * @param type
     *              type of the Upgrade
     */
    void canUpgrade(UpgradeType type);
    /**
     * Upgrade Life
     */
    void upgradeLife();
    /**
     * Upgrade Speed
     */
    void upgradeSpeed();
    /**
     * Upgrade Damage
     */
    void upgradeDamage();
    /**
     *Upgrade Range
     */
    void upgradeRange();
    /**
     * change price to the giving upgrade
     * @param type
     *              type of the Upgrade
     */
    void changePrice(UpgradeType type);
}
