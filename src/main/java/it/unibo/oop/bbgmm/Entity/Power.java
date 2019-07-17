package it.unibo.oop.bbgmm.Entity;

/**
 * This interface represents the specific power associated to PowerUp
 */
public interface Power {

    void activate();

    void update();

    void deactivate();

    PowerTag getPowerTag();
}
