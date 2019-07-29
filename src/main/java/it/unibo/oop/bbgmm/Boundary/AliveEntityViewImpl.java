package it.unibo.oop.bbgmm.Boundary;


import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public class AliveEntityViewImpl extends EntityChangeStateView<PossibleEntityState> implements AliveEntityView {

    public AliveEntityViewImpl(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }

    @Override
    public void deathView() {
        removeFromView();
    }
}
