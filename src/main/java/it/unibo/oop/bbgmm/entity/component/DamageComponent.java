package it.unibo.oop.bbgmm.entity.component;

public class DamageComponent extends AbstractEntityComponent implements Damage {
    private final int damage;

    public DamageComponent(final int damage){
        this.damage = damage;
    }
    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void update(final double delta) {

    }
}
