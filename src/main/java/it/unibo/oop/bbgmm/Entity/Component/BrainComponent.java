package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Movement;
import javafx.geometry.Point2D;

/**
 * permite to follow a determinate entity and change the desired direciton
 */
public class BrainComponent extends AbstractEntityComponent implements Brain {

    private Entity entityToFollow;
    private Point2D nextPosition;


    /**
     *
     * @param entityToFollow
     */
    public BrainComponent(final Entity entityToFollow) {
        super();
        this.entityToFollow = entityToFollow;
        this.nextPosition = entityToFollow.getBody().getPosition();
    }


    @Override
    public void update(double delta) {
        followEntity();
    }

    /**
     * Follow the entity
     */
    public void followEntity(){

        getOwner().get().get(Movement.class).ifPresent(movement -> {

            this.nextPosition = this.entityToFollow.getBody().getPosition();

        final Point2D direction = this.nextPosition.subtract(getOwner().get().getBody().getPosition());
        movement.move(direction);
        });
    }
}
