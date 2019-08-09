package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.EndLevelController;
import it.unibo.oop.bbgmm.Control.PlayerInputListener;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import org.mapeditor.core.TileLayer;

/**
 * Interface that represent the view of the {@link it.unibo.oop.bbgmm.Entity.GameField}.
 */
public interface GameFieldView extends  ObservableView<EndLevelController> {
    /**
     * @return a factory for the views of entities
     */
    EntityViewFactory getEntityViewFactory();
    /**
     * Shows the tiled map.
     * @param layer
     *      Layer to show
     * @param topLeft
     *      Top left point
     * @param tileSize
     *      Dimension of the tile
     */
    void showField(TileLayer layer, Point2D topLeft, Dimension2D tileSize);

    /**
     * Getter for the Group.
     * @return {@link Group}
     */
    Group getGroup();

    /**
     * Sets a listener for player input.
     *
     * @param playerInputListener
     *      The listener to set
     */
    void setPlayerInputListener(PlayerInputListener playerInputListener);

    /**
     * Getter for the upgrade Button.
     * @return {@link Button}
     */
    Button getUpgradeButton();

    /**
     * Shows a box when a level is finished.
     * @param principalController
     *      {@link PrincipalController} instance
     */
    void showEndLevelBox(PrincipalController principalController);
}
