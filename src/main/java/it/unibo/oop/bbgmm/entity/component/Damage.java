package it.unibo.oop.bbgmm.entity.component;

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
