package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;

public class Body extends AbstractEntityComponent implements EntityBody{

    private Pair<Double, Double> position;
    private Pair<Double, Double> dimension;
    private double velocity;

    Body(Pair<Double, Double> position, Pair<Double, Double> dimension, double velocity) {
        super();
        this.position = position;
        this.dimension = dimension;
        this.velocity = velocity;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getVelocity() {
        return this.velocity;
    }

    @Override
    public Pair<Double, Double> getDimension() {
        return this.dimension;
    }
}
