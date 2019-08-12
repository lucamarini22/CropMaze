package it.unibo.oop.bbgmm.boundary;


import it.unibo.oop.bbgmm.entity.Direction;

public interface AliveEntityView extends EntityChangeState<PossibleEntityState> {

    /**
     * Remove the entity from the view
    */
    void deathView();

    /**
     *
     * @return  the current entity state
     */
    PossibleEntityState getCurrentState();

    /**
     * Used to change the entity view direction.
     *
     * @param direction
     *            The direction to set for the entity view.
     */
    void changeFaceDirection(Direction direction);

}
