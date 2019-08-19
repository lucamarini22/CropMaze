package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.entity.UpgradeType;

/**
 * Represents a controller for the upgrades.
 */
public interface UpgradeController {

    /**
     * Method used to update the view.
     */
    void updateView();

    /**
     * Upgrade the player on the specific type.
     * @param type
     *       the upgrade type
     */
    void upgradePlayer(UpgradeType type);
}
