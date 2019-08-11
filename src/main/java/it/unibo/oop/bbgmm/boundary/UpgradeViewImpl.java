package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.UpgradeController;
import it.unibo.oop.bbgmm.entity.UpgradeType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UpgradeViewImpl implements UpgradeView {

    private UpgradeController controller;

    @FXML
    private Button UpgradeLife;

    @FXML
    private Button UpgradeDamage;

    @FXML
    private Label LifeCost;

    @FXML
    private Label DamageCost;

    @FXML
    private Button UpgradeSpeed;

    @FXML
    private Button UpgradeRange;

    @FXML
    private Label SpeedCost;

    @FXML
    private Label RangeCost;

    @FXML
    void upgradeDamage() { controller.upgradePlayer(UpgradeType.DAMAGE);}

    @FXML
    void upgradeLife() { controller.upgradePlayer(UpgradeType.LIFE); }

    @FXML
    void upgradeRange() { controller.upgradePlayer(UpgradeType.RANGE);}

    @FXML
    void upgradeSpeed() { controller.upgradePlayer(UpgradeType.SPEED);}


    @Override
    public void setVisible(UpgradeType type, boolean visible) {
        switch(type){
            case LIFE:
                this.UpgradeLife.setDisable(visible);
                break;
            case RANGE:
                this.UpgradeRange.setDisable(visible);
                break;
            case DAMAGE:
                this.UpgradeDamage.setDisable(visible);
                break;
            case SPEED:
                this.UpgradeSpeed.setDisable(visible);
                break;
        }
    }

    @Override
    public void setPrice(UpgradeType type, int price, int currentMoney) {
        switch(type){
            case LIFE:
                this.LifeCost.setText(currentMoney + " / " + price);
                break;
            case RANGE:
                this.RangeCost.setText(currentMoney+ " / " + price);
                break;
            case DAMAGE:
                this.DamageCost.setText(currentMoney + " / " + price);
                break;
            case SPEED:
                this.SpeedCost.setText(currentMoney + " / " + price);
                break;
        }
    }

    @Override
    public void setController(UpgradeController controller) {
        this.controller = controller;
    }
}
