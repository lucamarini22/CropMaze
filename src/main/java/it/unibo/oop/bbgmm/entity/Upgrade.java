package it.unibo.oop.bbgmm.entity;

/**
 * It allows to upgrade the player's statistics.
 */
public interface Upgrade {

    /**
     * Verify if can be applied the upgrade.
     * @param type
     *      the upgrade type
     * @return if can be upgraded
     */
    boolean canUpgrade(UpgradeType type);
    /**
     * Upgrade Life.
     */
    void upgradeLife();
    /**
     * Upgrade Speed.
     */
    void upgradeSpeed();
    /**
     * Upgrade Damage.
     */
    void upgradeDamage();
    /**
     *Upgrade Range.
     */
    void upgradeRange();
    /**
     * change price to the giving upgrade.
     * @param type
     *       type of the Upgrade
     */
    void changePrice(UpgradeType type);

    /**
     * getter for the current player's money.
     * @return the current player's money
     */
    int getCurrentMoney();

    /**
     * getter for the specific upgrade type.
     * @param type
     *      the upgrade type
     * @return the upgrade type price
     */
    int getCurrentPrice(UpgradeType type);
}
