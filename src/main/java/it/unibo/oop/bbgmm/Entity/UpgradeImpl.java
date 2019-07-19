package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.Bag;
import it.unibo.oop.bbgmm.Entity.Component.Feet;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;

import java.util.HashMap;
import java.util.Map;

public class UpgradeImpl implements Upgrade {

    private Map<UpgradeType, Integer> values;
    private Entity player;

    public UpgradeImpl(Entity player){
        values = new HashMap<>();
        for (UpgradeType u : UpgradeType.values()) {
            values.put(u, 10);
        }
        this.player = player;
    }
    @Override
    public void canUpgrade(UpgradeType type) {
        player.get(Bag.class).ifPresent(bag -> {
            final int money = bag.getMoney();
            if(values.get(type)<=money){
                switch (type) {
                    case LIFE:
                        upgradeLife();
                        break;
                    case SPEED:
                        upgradeSpeed();
                        break;
                    case DAMAGE:
                        upgradeDamage();
                        break;
                    case RANGE:
                        upgradeRange();
                        break;
                }
                bag.addMoney(-values.get(type));
            }
        });
    }

    @Override
    public void upgradeLife() {
        player.get(Life.class).ifPresent( life ->
                life.incrementLife(20)
        );
        changePrice(UpgradeType.LIFE);
    }

    @Override
    public void upgradeSpeed() {
        player.get(Feet.class).ifPresent( feet -> {
            double speed = feet.getSpeed();
            feet.setSpeed(speed + 0.5);
        });
        changePrice(UpgradeType.SPEED);
    }

    @Override
    public void upgradeDamage() {
        player.get(Weapon.class).ifPresent(w -> {
            int damage = w.getWeaponDamage();
            w.setWeaponDamage(damage + 10);
        });
        changePrice(UpgradeType.DAMAGE);
    }

    @Override
    public void upgradeRange() {
        player.get(Weapon.class).ifPresent( w -> {
            int range = w.getWeaponRange();
            w.setWeaponRange(range + 1);
        });
    }

    @Override
    public void changePrice(UpgradeType type) {
        this.values.put(type, this.values.get(type) + 20);
    }
}
