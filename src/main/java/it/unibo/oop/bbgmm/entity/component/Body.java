package it.unibo.oop.bbgmm.entity.component;


import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Basic class for the body component.
 */
public final class Body extends AbstractEntityComponent implements EntityBody {

    private Point2D position;
    private final Dimension2D dimension;
    private final Rectangle shape;
    private Direction direction;



    Body(final Point2D position, final Dimension2D dimension, final Direction direction) {
        super();
        this.direction = direction;
        this.position = position;
        this.dimension = dimension;
        this.shape = new Rectangle(position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public void update(final double delta) { }


    @Override
    public Direction getDirection() {
        return this.direction;
    }


    @Override
    public void addPosition(final Point2D position) {
        this.position = this.position.add(position);
    }


    @Override
    public void changeDirection(final Direction direction) {
        this.direction = direction;
    }


    @Override
    public Point2D getPosition() {
        return this.position;
    }


    @Override
    public Rectangle getShape() {
        return this.shape;
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }
}
