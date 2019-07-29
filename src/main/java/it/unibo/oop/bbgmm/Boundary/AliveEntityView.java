package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.Direction;

public interface AliveEntityView extends EntityChangeState<PossibleEntityState>, EntityView {

    /**
     * Use to change the entity direction
     * @param direction
     *          the direction to set for the entity
     */
    void changeFaceDirection(Direction direction);

    /**
     * starts the death animation for the entity
     */
    void deathUpdate();
}
