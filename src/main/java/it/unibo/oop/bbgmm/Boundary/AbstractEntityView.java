package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;


public abstract class AbstractEntityView implements EntityView {
    private final ImageView image = new ImageView();
    private Dimension2D dimension;
    private final Group myGroup;

    public AbstractEntityView(final Group group, final Dimension2D dimension){
        this.myGroup = group;
        this.dimension = dimension;
        //setDimension(dimension);
        this.myGroup.getChildren().add(image);
    }

    @Override
    public void setPosition(final Point2D newPosition) {
        this.image.setTranslateX(newPosition.getX() - this.image.getBoundsInLocal().getWidth() / 2);
        this.image.setTranslateY(newPosition.getY() - this.image.getBoundsInLocal().getHeight() / 2);
    }

    @Override
    public void setDimension(final Dimension2D dimension) {
        image.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        image.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    @Override
    public Point2D getPosition() {
        return new Point2D(this.image.getTranslateX(),image.getTranslateY());
    }

    @Override
    public void removeFromView() {
        this.myGroup.getChildren().remove(this.image);
    }

    @Override
    public ImageView getView() {
        return this.image;
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }

    @Override
    public Group getGroup() {
        return this.myGroup;
    }
}
