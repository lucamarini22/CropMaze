package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.Set;


public class Bullet extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.5,0.3);

    public Bullet(final BodyBuilder bodyBuilder,
                  final Weapon weapon,
                  final Direction ownerDirection,
                  final Point2D position,
                  final Set<Entity> walls) {
        super(bodyBuilder.setPosition(position)
                         .setDimension(SIZE)
                         .setDirection(ownerDirection)
                         .setMovable(true)
                         .build());
        add(new LifeComponent(weapon.getWeaponRange()));
        add(new LimitedBulletFeet(weapon, weapon.getWeaponSpeed(), get(Life.class).get(), walls));
        add(new DamageComponent(weapon.getWeaponDamage()));
        add(new ClashComponent());
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.SHOT));
    }
}
