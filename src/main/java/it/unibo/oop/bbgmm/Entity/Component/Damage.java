package it.unibo.oop.bbgmm.Entity.Component;

/**
 * Manage the entity (alien, bullet) damage
 */
public interface Damage extends EntityComponent{
    /**
     *
     * @return  The current amount of damage
     */
    double getDamage();

    /**
     *
     * @param damage
     *      New damage dealt by the entity
     */
    void setDamage(double damage);
}
