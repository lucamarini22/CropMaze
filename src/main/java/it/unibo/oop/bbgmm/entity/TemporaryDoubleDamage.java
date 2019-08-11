package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.Weapon;

/**
 * This power gives double damage to the weapon's player.
 */
public final class TemporaryDoubleDamage extends TemporaryPower {

    private static final PowerTag POWER_TAG = PowerTag.DOUBLEDAMAGE;

    /**
     * This constructor set the timeout.
     * @param timeout
     *      the duration of the power
     */
    public TemporaryDoubleDamage(final double timeout) {
        super(timeout, POWER_TAG);
    }

    @Override
    public void activate(final Entity player) {
        super.activate(player);
        player.get(Weapon.class).ifPresent(w -> {
            final int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage * 2);
        });
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getPlayer().get(Weapon.class).ifPresent(w -> {
            final int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage / 2);
        });
    }
}
