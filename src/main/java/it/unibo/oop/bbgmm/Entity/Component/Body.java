package it.unibo.oop.bbgmm.Entity.Component;


import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public class Body extends AbstractEntityComponent implements EntityBody{

    private final Point2D position;
    private final Dimension2D dimension;
    private final Rectangle2D shape;
    private final Direction direction;
    private final boolean movable;



    Body(Point2D position, Dimension2D dimension, Direction direction,boolean movable) {
        super();
        this.movable = movable;
        this.direction = direction;
        this.position = position;
        this.dimension = dimension;
        this.shape = new Rectangle2D(position.getX(),position.getY(),dimension.getWidth(),dimension.getHeight());
    }

    @Override
    public void update(double delta) {}

    @Override
    public boolean canMove() {return this.movable;}


    @Override
    public Direction getDirection() {return this.direction;}

    @Override
    public Point2D getPosition() {return this.position;}

    @Override
    public Rectangle2D getShape() {return this.shape;}

    @Override
    public Dimension2D getDimension() {return this.dimension;}
}
