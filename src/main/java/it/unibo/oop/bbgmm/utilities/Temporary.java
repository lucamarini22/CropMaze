package it.unibo.oop.bbgmm.utilities;

/**
 *This interface represents an object which have a limited life time.
 **/
public interface Temporary {
    /**
     * Returns a double that represents the time left.
     * @return
     *      the remaining time.
     */
    double getRemainingTime();
    /**
     * Give more time to the power.
     * @param time
     *      the time to be added to the power.
     */
    void addTime(double time);
}
