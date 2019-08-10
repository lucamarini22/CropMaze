package it.unibo.oop.bbgmm.entity;

public class DeathEvent {

    private final Entity entity;

    public DeathEvent(final Entity entity){
        this.entity=entity;
    }

    public Entity getEntity(){
        return this.entity;
    }
}
