package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.Bag;
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
            values.put(u, 1);
        }
        this.player = player;
    }
    @Override
    public boolean canUpgrade(UpgradeType type) {
        if(this.values.get(type) > this.player.get(Bag.class).get().getMoney()){
            return true;
        }
        return false;
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
        player.get(Movement.class).ifPresent( feet -> {
            feet.setSpeed(feet.getSpeed() + 0.5);
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
            w.setWeaponRange(w.getWeaponRange() + 1);
        });
        changePrice(UpgradeType.RANGE);
    }

    @Override
    public void changePrice(UpgradeType type) {
        this.player.get(Bag.class).ifPresent(b -> {
            b.addMoney(-this.values.get(type));
        });
        this.values.put(type, this.values.get(type) * 2);
    }

    public int getCurrentMoney(){
        return this.player.get(Bag.class).get().getMoney();
    }

    public int getCurrentPrice(UpgradeType upgradeType){
        return this.values.get(upgradeType);
    }

}
