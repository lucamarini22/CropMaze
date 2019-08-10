package it.unibo.oop.bbgmm.entity;

/**
 * This interface represents the specific power associated to PowerUp
 */
public interface Power {
    /**
     * Activate the power.
     */
    void activate(Entity player);
    /**
     * Notify the time passed.
     * @param dt
     *      The time passed.
     */
    void update(double dt);
    /**
     * Deactivate the power.
     */
    void deactivate();
    /**
     * Return the PowerTag.
     * @return
     *      The PowerTag.
     */
    PowerTag getPowerTag();

    /**
     * Return the power's state
     * @return
     *      the powers's state
     */
    boolean isActive();
}
