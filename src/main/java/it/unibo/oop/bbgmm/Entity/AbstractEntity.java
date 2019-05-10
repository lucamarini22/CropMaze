package it.unibo.oop.bbgmm.Entity;

/**
 * base class for entity type
 */

public abstract class AbstractEntity {
    private final EntityBody body;

    /**
     * constructor for Abstract Entity
     * @param body
     */
    public AbstractEntity(EntityBody body) {
        this.body = body;
        body.attach(this);
    }

    @Override
    public final EntityBody getBody(){
        return body;
    }

    @Override
    public void update(final double up){
        update(up);
    }

    /**
     * generate a @DestructionEvent and detaches all components
     */
    @Override
    public void destroy(){
        post(new DestructionEvent(this));
        components.forEach(this::remove);
        remove(body);
    }


}
