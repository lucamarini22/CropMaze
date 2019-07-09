package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


public interface EntityBody extends EntityComponent{

    /**
     * @return The position
     */
    Point2D getPosition();

    /**
     *
     * @return Body dimension
     */
    Dimension2D getDimension();

}
