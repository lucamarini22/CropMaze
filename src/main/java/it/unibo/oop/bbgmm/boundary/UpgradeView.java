package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.UpgradeController;
import it.unibo.oop.bbgmm.entity.UpgradeType;

/**
 * Upgrade view interface
 */
public interface UpgradeView {

    /**
     * Set visible or not the upgrade Buttons
     * @param type
     *       the Upgrade type
     * @param visible
     *
     */
    void setVisible(UpgradeType type, boolean visible);

    /**
     * Set the cost of the Upgrades
     * @param type
     *       the Upgrade type
     * @param price
     *       the current upgrade type's price
     * @param currentMoney
     *       the current player's money
     */
    void setPrice(UpgradeType type, int price, int currentMoney);

    /**
     * Set the controller to the view
     * @param controller
     *       the view's controller
     */
    void setController(UpgradeController controller);
}
