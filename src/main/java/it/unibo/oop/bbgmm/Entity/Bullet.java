package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


public class Bullet extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1.1,1.2);

    public Bullet(final BodyBuilder bodyBuilder, final Weapon weapon, final Direction ownerDirection, final int weaponRange, final int weaponDamage, final Point2D position, int speed) {
        super(bodyBuilder.setPosition(position)
                         .setDimension(SIZE)
                         .setDirection(ownerDirection)
                         .setMovable(true)
                         .build());
        add(new LifeComponent(weaponRange));
        add(new LimitedBulletFeet(weapon, speed));
        add(new DamageComponent(weaponDamage));
        add(new ClashComponent());
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.SHOT));
    }
}
