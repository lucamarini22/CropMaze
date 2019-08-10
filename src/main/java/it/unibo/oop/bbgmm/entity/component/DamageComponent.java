package it.unibo.oop.bbgmm.entity.component;

public class DamageComponent extends AbstractEntityComponent implements Damage {
    private int damage;

    public DamageComponent(final int damage){
        this.damage = damage;
        System.out.println("damage " + damage);
    }
    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void update(double delta) {

    }
}
