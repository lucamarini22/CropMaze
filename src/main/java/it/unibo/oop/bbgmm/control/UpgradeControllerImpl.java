package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.UpgradeView;
import it.unibo.oop.bbgmm.entity.Entity;
import it.unibo.oop.bbgmm.entity.Upgrade;
import it.unibo.oop.bbgmm.entity.UpgradeImpl;
import it.unibo.oop.bbgmm.entity.UpgradeType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The implementation of {@link UpgradeController}.
 */
public final class UpgradeControllerImpl implements UpgradeController {

    private UpgradeView upgradeView;
    private final Upgrade upgrade;

    /**
     * Creates a new {@link UpgradeController} instance.
     * @param upgradeButton
     *      the upgrade Button in the {@link it.unibo.oop.bbgmm.boundary.GameFieldView}
     * @param player
     *      the {@link it.unibo.oop.bbgmm.entity.Player} instance
     * @param gameController
     *      the {@link GameController} instance
     * @param primaryStage
     *      the primary stage
     */
    public UpgradeControllerImpl(final Button upgradeButton, final Entity player, final GameController gameController,
                                 final Stage primaryStage) {
        upgrade = new UpgradeImpl(player);

        upgradeButton.setOnMouseClicked(event -> {
            final FXMLLoader loader = new FXMLLoader();
            try {
                final Parent p = loader.load(getClass().getResourceAsStream("/scene/upgrade.fxml"));
                final Stage upgradeScreen = new Stage();
                upgradeScreen.initOwner(primaryStage);
                upgradeView = loader.getController();
                upgradeView.setController(this);
                upgradeScreen.setTitle("UPGRADES");
                upgradeScreen.getIcons().add(new Image("images/mainMenu/icon.png"));
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
        for (final UpgradeType type : UpgradeType.values()) {
            this.upgradeView.setVisible(type, this.upgrade.canUpgrade(type));
            this.upgradeView.setPrice(type, this.upgrade.getCurrentPrice(type), this.upgrade.getCurrentMoney());
        }
    }

    @Override
    public void upgradePlayer(final UpgradeType type) {
        switch (type) {
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
                default:
        }
        this.updateView();
    }
}
