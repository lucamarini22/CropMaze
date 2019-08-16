package it.unibo.oop.bbgmm.entity.component;

/**
 * Basic class for damage component.
 */
public final class DamageComponent extends AbstractEntityComponent implements Damage {
    private final int damage;

    /**
     * @param damage
     *      The component's damage
     */
    public DamageComponent(final int damage) {
        super();
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
