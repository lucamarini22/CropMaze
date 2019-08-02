package it.unibo.oop.bbgmm.Boundary;


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
}
