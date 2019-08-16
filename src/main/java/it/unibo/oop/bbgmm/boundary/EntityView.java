package it.unibo.oop.bbgmm.boundary;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Interface for entity view.
 */
public interface EntityView {

    /**
     * Change the entity view position.
     *
     * @param newPosition
     *      The entity view new position.
     */
    void setPosition(Point2D newPosition);

    /**
     * Set the entity view dimension.
     * @param dimension
     *      The entity view dimension.
     */
    void setDimension(Dimension2D dimension);


    /**
     * @return
     *      Current entity view position.
     */
    Point2D getPosition();

    /**
     * Remove the entity from the view.
     */
    void removeFromView();

    /**
     * @return
     *      The entity image view.
     */
    ImageView getView();

    /**
     * @return
     *      The group to which the entity belongs.
     */
    Group getGroup();

    /**
     * @return
     *      The entity view dimension.
     */
    Dimension2D getDimension();
}
