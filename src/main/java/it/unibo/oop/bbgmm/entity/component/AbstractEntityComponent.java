package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Entity;

import java.util.Optional;

/**
 * Manage a component.
 */
public abstract class AbstractEntityComponent implements EntityComponent {

    private Optional<Entity> owner = Optional.empty();


    /**
     * {@inheritDoc}
     */
    @Override
    public void attach(final Entity owner) {
        if (this.owner.isPresent()) {
            throw new IllegalStateException("This component is already attached to an entity");
        }
        this.owner = Optional.of(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detach() {
        this.owner.ifPresent(entity -> {
            owner = Optional.empty();
        });
    }

    @Override
    public void update(final double delta) {
        //
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<? extends Entity> getOwner() {
        return this.owner;
    }


}
