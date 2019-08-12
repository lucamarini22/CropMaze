package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.UpgradeController;
import it.unibo.oop.bbgmm.entity.UpgradeType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * It represents the {@link UpgradeView} implementation.
 */
public final class UpgradeViewImpl implements UpgradeView {

    private static final String SLASH = " / ";
    private UpgradeController controller;

    @FXML
    private Button upgradeLifeButton;

    @FXML
    private Button upgradeDamageButton;

    @FXML
    private Label lifeCost;

    @FXML
    private Label damageCost;

    @FXML
    private Button upgradeSpeedButton;

    @FXML
    private Button upgradeRangeButton;

    @FXML
    private Label speedCost;

    @FXML
    private Label rangeCost;

    /**
     * Method that upgrade the {@link it.unibo.oop.bbgmm.entity.Player} damage.
     */
    @FXML
    public void upgradeDamage() {
        controller.upgradePlayer(UpgradeType.DAMAGE);
    }

    /**
     * Method that refill the {@link it.unibo.oop.bbgmm.entity.Player} life.
     */
    @FXML
    public void upgradeLife() {
        controller.upgradePlayer(UpgradeType.LIFE);
    }

    /**
     * Method that upgrade the {@link it.unibo.oop.bbgmm.entity.Player} range.
     */
    @FXML
    public void upgradeRange() {
        controller.upgradePlayer(UpgradeType.RANGE);
    }

    /**
     * Method that upgrade the {@link it.unibo.oop.bbgmm.entity.Player} speed.
     */
    @FXML
    public void upgradeSpeed() {
        controller.upgradePlayer(UpgradeType.SPEED);
    }


    @Override
    public void setVisible(final UpgradeType type, final boolean visible) {
        switch (type) {
            case LIFE:
                this.upgradeLifeButton.setDisable(visible);
                break;
            case RANGE:
                this.upgradeRangeButton.setDisable(visible);
                break;
            case DAMAGE:
                this.upgradeDamageButton.setDisable(visible);
                break;
            case SPEED:
                this.upgradeSpeedButton.setDisable(visible);
                break;
                default:
        }
    }

    @Override
    public void setPrice(final UpgradeType type, final int price, final int currentMoney) {
        switch (type) {
            case LIFE:
                this.lifeCost.setText(currentMoney + SLASH + price);
                break;
            case RANGE:
                this.rangeCost.setText(currentMoney + SLASH + price);
                break;
            case DAMAGE:
                this.damageCost.setText(currentMoney + SLASH + price);
                break;
            case SPEED:
                this.speedCost.setText(currentMoney + SLASH + price);
                break;
                default:
        }
    }

    @Override
    public void setController(final UpgradeController controller) {
        this.controller = controller;
    }
}
