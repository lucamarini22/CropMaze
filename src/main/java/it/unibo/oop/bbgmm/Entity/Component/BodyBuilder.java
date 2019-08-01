package it.unibo.oop.bbgmm.Entity.Component;


import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;



public class BodyBuilder {
    private Dimension2D dimension;
    private Point2D position;
    private Direction direction;
    private boolean movable = false;


    public BodyBuilder setDirection(final Direction direction){
        this.direction = direction;
        return this;
    }
    public BodyBuilder setDimension(final Dimension2D dimension) {
        this.dimension = dimension;
        return this;
    }

    public BodyBuilder setMovable(final boolean movable){
        this.movable = movable;
        return this;
    }
    public BodyBuilder setPosition(final Point2D position) {
        this.position = position;
        return this;
    }

    public Body build() {
        if(this.checkBuild()){
            throw new IllegalStateException("The build is incomplete");
        }else {
            Body body = new Body(this.position, this.dimension, this.direction, this.movable);
            return body;
        }
    }

    private boolean checkBuild(){
        return dimension == null || this.position == null || this.direction == null;
    }


}
