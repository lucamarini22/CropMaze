package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Boundary.ViewUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public abstract class AbstractEntityView implements EntityView {

    private final ImageView view = new ImageView();
    private final Dimension2D dimension;
    private final Group group;

    /**
     *
     * @param dimension
     *          instance where entity view is collocated
     * @param group
     *          entity view dimension
     *
     */
    public AbstractEntityView(final Dimension2D dimension, final Group group) {
        this.dimension = dimension;
        this.group = group;

        group.getChildren().add(view);
    }

    @Override
    public void setPosition(Point2D newPosition) {
        view.setTranslateX(newPosition.getX() - view.getBoundsInLocal().getWidth() / 2);
        view.setTranslateY(newPosition.getY() - view.getBoundsInLocal().getHeight());

    }

    @Override
    public void setDimension(Dimension2D dimension) {
        view.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        view.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    @Override
    public Point2D getPosition() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    @Override
    public void removeFromView() {
        group.getChildren().remove(view);
    }

    @Override
    public ImageView getView() {
        return view;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }
}
