package it.unibo.oop.bbgmm.Entity.Component;


import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


public class BodyBuilder {
    private Dimension2D dimension;
    private Point2D position;


    public BodyBuilder setDimension(final Dimension2D dimension) {
        this.dimension = dimension;
        return this;
    }

    public BodyBuilder setPosition(final Point2D position) {
        this.position = position;
        return this;
    }

    public Body build() {
        Body body = new Body(this.position, this.dimension, this.velocity);
        return body;
    }

}
