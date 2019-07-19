package it.unibo.oop.bbgmm.Entity.Component;

public class DamageComponent extends AbstractEntityComponent implements Damage {
    private int damage;

    public DamageComponent(final int damage){
        this.damage = damage;
    }
    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void update(double delta) {

    }
}
