package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.mapeditor.core.TileLayer;

/**
 * Interface that represent the view of the {@link it.unibo.oop.bbgmm.Entity.GameField}.
 */
public interface GameFieldView {

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
}
