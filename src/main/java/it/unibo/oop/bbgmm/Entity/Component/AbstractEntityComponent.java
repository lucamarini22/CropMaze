package it.unibo.oop.bbgmm.Entity.Component.;

import it.unibo.oop.bbgmm.Entity.Entity;

import java.util.Optional;

public abstract class AbstractEntityComponent implements EntityComponent {

    Optional<? extends Entity> owner = Optional.empty();

    @Override
    public void attach(Entity owner) {
        if(this.owner.isPresent()){
            throw new IllegalStateException("This component is already attached to an entity");
        }
        this.owner = Optional.of(owner);
        this.owner.register(this); //Dice all'owner di registrare il componente
    }

    @Override
    public void detach() {
        if(this.owner.isPresent()){
            this.owner.remove(this);
            this.owner = Optional.empty();
        }
    }

    @Override
    public Optional<? extends Entity> getOwner() {
        return this.owner;
    }

    protected boolean detachOnDeath

}
