package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.Weapon;

/**
 * This power gives double damage to the weapon's player
 */
public class TemporaryDoubleDamage extends TemporaryPower {

    private static final PowerTag powerTag = PowerTag.DOUBLEDAMAGE;

    public TemporaryDoubleDamage(double timeout) {
        super(timeout, powerTag);
    }

    @Override
    public void activate(Entity player) {
        super.activate(player);
        player.get(Weapon.class).ifPresent(w -> {
            int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage * 2);
        });
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getPlayer().get(Weapon.class).ifPresent(w -> {
            int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage/2);
        });
    }
}
