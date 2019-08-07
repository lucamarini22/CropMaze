package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.UpgradeView;
import it.unibo.oop.bbgmm.Boundary.UpgradeViewImpl;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Upgrade;
import it.unibo.oop.bbgmm.Entity.UpgradeImpl;
import it.unibo.oop.bbgmm.Entity.UpgradeType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class UpgradeControllerImpl implements UpgradeController{

    private UpgradeView upgradeView;
    private final Upgrade upgrade;
    private final GameController gameController;

    public UpgradeControllerImpl(final Button upgradeButton,final Entity player, final GameController gameController,
                                 final Stage primaryStage){
        upgrade = new UpgradeImpl(player);
        this.gameController = gameController;

        upgradeButton.setOnMouseClicked( event -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                Parent p = loader.load(getClass().getResourceAsStream("/scene/upgrade.fxml"));
                Stage upgradeScreen = new Stage();
                upgradeScreen.initOwner(primaryStage);
                upgradeView = loader.getController();
                upgradeView.setController(this);
                upgradeScreen.setTitle("UPGRADES");
                upgradeScreen.setScene(new Scene(p));
                upgradeScreen.setResizable(false);
                upgradeScreen.show();
                gameController.stop();
                this.updateView();
                upgradeScreen.setOnCloseRequest(request -> {
                    gameController.restart();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateView() {
        for (UpgradeType type : UpgradeType.values()) {
            this.upgradeView.setVisible(type, this.upgrade.canUpgrade(type));
            this.upgradeView.setPrice(type, this.upgrade.getCurrentPrice(type), this.upgrade.getCurrentMoney());
        }
    }

    @Override
    public void upgradePlayer(UpgradeType type) {
        switch (type){
            case LIFE:
                this.upgrade.upgradeLife();
                break;
            case DAMAGE:
                this.upgrade.upgradeDamage();
                break;
            case SPEED:
                this.upgrade.upgradeSpeed();
                break;
            case RANGE:
                this.upgrade.upgradeRange();
                break;
        }
        this.updateView();
    }
}
