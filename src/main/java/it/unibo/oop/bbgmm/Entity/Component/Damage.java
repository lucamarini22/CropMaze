package it.unibo.oop.bbgmm.Entity.Component;

/**
 * Manage the entity (alien, bullet) damage
 */
public interface Damage extends EntityComponent{
    /**
     *
     * @return  The current amount of damage
     */
    int getDamage();
}
