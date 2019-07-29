package it.unibo.oop.bbgmm.Boundary;

<<<<<<< HEAD
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
=======
public interface AliveEntityView {
    /**
     * Remove the entity from the view
     */
    void deathView();
>>>>>>> 02dff5e65a74fd2e8fe0e3398b05f9ab92282505
}
