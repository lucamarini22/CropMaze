package it.unibo.oop.bbgmm.entity;

/**
 * Wrapper class that stores data relative to a Death Event.
 */
public final class DeathEvent {

    private final Entity entity;

    /**
     * Creates a new Death Event.
     * @param entity
     *      the {@link Entity} dead
     */
    public DeathEvent(final Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns the {@link Entity} that is dead.
     * @return
     *      the {@link Entity}
     */
    public Entity getEntity() {
        return this.entity;
    }
}
