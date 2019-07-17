package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Bullet extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1.1,1.2);

    public Bullet(final BodyBuilder bodyBuilder, final Direction ownerDirection, final int weaponRange, final int weaponDamage, final Point2D position, int speed) {
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .build());

        add(new Feet(speed));
    }


}
