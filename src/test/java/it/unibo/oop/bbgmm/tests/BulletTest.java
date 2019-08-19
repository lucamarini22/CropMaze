package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import it.unibo.oop.bbgmm.entity.component.Weapon;

import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.GameField;
import it.unibo.oop.bbgmm.entity.GameFieldImpl;
import it.unibo.oop.bbgmm.entity.Player;
import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.Wall;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test for Bullet class.
 */
public class BulletTest {

    private static final int X = 20;
    private static final int Y = 40;
    private static final int HEALTH = 100;
    private static final int INITIAL_STEP = 1;;
    private final Weapon weapon;
    private final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl(), null);

    /**
     * Constructor fot BulletTest.
     */
    public BulletTest() {
        this.weapon = new Player(new BodyBuilder(), new Point2D(X, Y), HEALTH, this.gameField).get(Weapon.class).get();
    }

    /**
     * Test bullet shot right.
     */
    @Test
    public void shootRight() {
        this.weapon.shoot(Direction.EAST);
        List<Bullet> list = this.weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(), 1);

        final Bullet bullet = list.get(0);
        final Point2D oldPosition = bullet.getBody().getPosition();

        final int steps = this.weapon.getWeaponRange();
        for (int i = 0; i < steps; i++) {
            //make the bullet move
            bullet.update(1);
        }

        list = this.weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(), 0);

        //check the bullet new position

        final Point2D newPosition = new Point2D(oldPosition.getX() + steps - INITIAL_STEP, oldPosition.getY());
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    /**
     * Test bullet shot left.
     */
    @Test
    public void shootLeft() {
        this.weapon.shoot(Direction.WEST);
        List<Bullet> list = this.weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(), 1);

        final Bullet bullet = list.get(0);
        final Point2D oldPosition = bullet.getBody().getPosition();

        final int steps = this.weapon.getWeaponRange();
        for (int i = 0; i < steps; i++) {
            //make the bullet move
            bullet.update(1);
        }

        list = this.weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(), 0);

        //check the bullet new position

        final Point2D newPosition = new Point2D(oldPosition.getX() - steps + INITIAL_STEP, oldPosition.getY());
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    /**
     * Test bullet shot up.
     */
    @Test
    public void shootUp() {
        this.weapon.shoot(Direction.NORTH);
        List<Bullet> list = this.weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(), 1);

        final Bullet bullet = list.get(0);
        final Point2D oldPosition = bullet.getBody().getPosition();

        final int steps = this.weapon.getWeaponRange();
        for (int i = 0; i < steps; i++) {
            //make the bullet move
            bullet.update(1);
        }

        list = this.weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(), 0);

        //check the bullet new position

        final Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY() + steps - INITIAL_STEP);
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    /**
     * Test bullet shot down.
     */
    @Test
    public void shootDown() {
        this.weapon.shoot(Direction.SOUTH);
        List<Bullet> list = this.weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(), 1);

        final Bullet bullet = list.get(0);
        final Point2D oldPosition = bullet.getBody().getPosition();

        final int steps = this.weapon.getWeaponRange();
        for (int i = 0; i < steps; i++) {
            //make the bullet move
            bullet.update(1);
        }

        list = this.weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(), 0);

        //check the bullet new position

        final Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY() - steps + INITIAL_STEP);
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    /**
     * Test bullet shot against a wall.
     */
    @Test
    public void shootWithWall() {
        //use only one direction because with the others it's the same

        final int steps = weapon.getWeaponRange();

        //creation of a wall
        final Point2D wallPosition = new Point2D(15, Y + steps / 2 + INITIAL_STEP);
        final Dimension2D wallDimension = new Dimension2D(20, 3);
        final Wall wall = new Wall(new BodyBuilder(), wallPosition, wallDimension);
        this.gameField.addEntity(wall);

        this.weapon.shoot(Direction.NORTH);
        List<Bullet> list = this.weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(), 1);

        final Bullet bullet = list.get(0);
        final Point2D oldPosition = bullet.getBody().getPosition();


        int i = 0;

        for (i = 0; i < steps; i++) {
            //make the bullet move
            bullet.update(1);
            if (this.weapon.getBulletList().size() == 0) {
                break;
            }
        }

        //the iteration must be interrupted by a collision with the wall
        Assert.assertNotEquals(i, steps - INITIAL_STEP);

        list = this.weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(), 0);

        //check the bullet new position

        final Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY() + steps - INITIAL_STEP);
        Assert.assertNotEquals(bullet.getBody().getPosition(), newPosition);
    }
}
