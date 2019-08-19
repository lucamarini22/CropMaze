package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Entity;

/**
 * Models a life component.
 */
public final class LifeComponent extends AbstractEntityComponent implements Life {

    private final int maxLifePoints;
    private int currentLifePoints;
    private boolean vulnerable = true;

    @Override
    public void setVulnerability(final boolean vulnerability) {
        this.vulnerable = vulnerability;
    }

    /**
     * @param max
     *      Max amount of life.
     */
    public LifeComponent(final int max) {
        super();
        this.maxLifePoints = max;
        this.currentLifePoints = this.maxLifePoints;
    }
    @Override
    public int getMaxLifePoints() {
        return this.maxLifePoints;
    }

    @Override
    public int getCurrentLifePoints() {
        return this.currentLifePoints;
    }

    @Override
    public boolean isVulnerable() {
        return vulnerable;
    }

    @Override
    public void damaged(final int damageAmount) {
        if (vulnerable) {
            this.currentLifePoints -= damageAmount;
            if (this.currentLifePoints < 0) {
                this.currentLifePoints = 0;
                this.getOwner().ifPresent(o -> ((Entity) o).removeEntity());
            }
        }
    }

    @Override
    public void incrementLife(final int moreLife) {
        this.currentLifePoints = this.currentLifePoints + moreLife;
        if (currentLifePoints > maxLifePoints) {
            currentLifePoints = maxLifePoints;
        }
    }

    @Override
    public boolean isAlive() {
        return this.currentLifePoints > 0;
    }

    @Override
    public boolean isDead() {
        return !this.isAlive();
    }
}
