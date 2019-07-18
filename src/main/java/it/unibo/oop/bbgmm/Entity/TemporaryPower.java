package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Temporary;

/**
 * This class represents a power that have a limited life time
 */
public abstract class TemporaryPower extends AbstractPower implements Temporary {

    private double time;
    private double timeout;

    public TemporaryPower(final double timeout, final PowerTag powerTag){
        super(powerTag);
        this.time=timeout;
        this.timeout=timeout;
    }

    @Override
    public void update(double dt) {
        if(this.isActive()){
            this.time = this.time - dt;
        }
        else
        {
            if(this.time < 0)
            {
                this.deactivate();
            }
        }
    }

    @Override
    public double getRemainingTimePercentage() {
        return this.time/this.timeout;
    }

    @Override
    public double getRemainingTime() {
        return this.time;
    }

    @Override
    public void addTime(double time) {
        this.time = this.time + time;
        this.timeout = this.timeout + time;
    }
}
