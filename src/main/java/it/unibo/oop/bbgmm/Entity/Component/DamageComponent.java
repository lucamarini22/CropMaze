package it.unibo.oop.bbgmm.Entity.Component;

public class DamageComponent extends AbstractEntityComponent implements Damage {
    private double damage;

    public DamageComponent(final double damage){
        this.damage = damage;
    }
    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public void update(double delta) {

    }
}
