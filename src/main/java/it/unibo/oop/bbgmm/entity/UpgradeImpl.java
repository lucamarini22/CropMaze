package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.Bag;
import it.unibo.oop.bbgmm.entity.component.Life;
import it.unibo.oop.bbgmm.entity.component.Weapon;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Upgrade} implementation.
 */
public final class UpgradeImpl implements Upgrade {

    private final Map<UpgradeType, Integer> values;
    private final Entity player;

    /**
     * {@link Upgrade} constructor.
     * @param player
     *      the player instance
     */
    public UpgradeImpl(final Entity player) {
        values = new HashMap<>();
        for (final UpgradeType u : UpgradeType.values()) {
            values.put(u, 1);
        }
        this.player = player;
    }
    @Override
    public boolean canUpgrade(final UpgradeType type) {
        return this.values.get(type) > this.player.get(Bag.class).get().getMoney();
    }

    @Override
    public void upgradeLife() {
        player.get(Life.class).ifPresent(life ->
               life.incrementLife(life.getMaxLifePoints() - life.getCurrentLifePoints())
        );
        changePrice(UpgradeType.LIFE);
    }

    @Override
    public void upgradeSpeed() {
        player.get(Movement.class).ifPresent(feet -> {
            feet.setSpeed(feet.getSpeed() + 0.5);
        });
        changePrice(UpgradeType.SPEED);
    }

    @Override
    public void upgradeDamage() {
        player.get(Weapon.class).ifPresent(w -> {
            final int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage + 10);
        });
        changePrice(UpgradeType.DAMAGE);
    }

    @Override
    public void upgradeRange() {
        player.get(Weapon.class).ifPresent(w -> {
            w.setWeaponRange(w.getWeaponRange() + 1);
        });
        changePrice(UpgradeType.RANGE);
    }

    @Override
    public void changePrice(final UpgradeType type) {
        this.player.get(Bag.class).ifPresent(b -> {
            b.addMoney(-this.values.get(type));
        });
        this.values.put(type, this.values.get(type) * 2);
    }

    @Override
    public int getCurrentMoney() {
        return this.player.get(Bag.class).get().getMoney();
    }

    @Override
    public int getCurrentPrice(final UpgradeType upgradeType) {
        return this.values.get(upgradeType);
    }

}
