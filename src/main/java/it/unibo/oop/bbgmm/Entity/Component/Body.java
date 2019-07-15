package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


public class Body extends AbstractEntityComponent implements EntityBody{

    private Point2D position;
    private Dimension2D dimension;

    Body(Point2D position, Dimension2D dimension, double velocity) {
        super();
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }
}
