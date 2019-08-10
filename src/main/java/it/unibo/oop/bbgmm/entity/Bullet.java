package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import it.unibo.oop.bbgmm.entity.component.DamageComponent;
import it.unibo.oop.bbgmm.entity.component.LifeComponent;
import it.unibo.oop.bbgmm.entity.component.Weapon;
import it.unibo.oop.bbgmm.entity.component.CollisionComponent;
import it.unibo.oop.bbgmm.entity.component.ClashComponent;
import it.unibo.oop.bbgmm.entity.component.LimitedBulletFeet;
import it.unibo.oop.bbgmm.entity.component.Life;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * Bullet entity.
 */
public class Bullet extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1.3, 1.3);

    /**
     * Constructor for Bullet.
     *
     * @param bodyBuilder
     *          The bodyBuilder
     * @param weapon
     *          The weapon used by the player
     * @param ownerDirection
     *          The direction of the player
     * @param position
     *          The position of the bullet
     * @param walls
     *          The set of walls
     */
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
        add(new DamageComponent(weapon.getWeaponDamage()));
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.SHOT));
        add(new ClashComponent());
        add(new LimitedBulletFeet(weapon, ownerDirection, weapon.getWeaponSpeed(), get(Life.class).get(), walls));
    }
}
