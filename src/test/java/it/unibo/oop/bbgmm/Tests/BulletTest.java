package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class BulletTest {

    private static final int INITIAL_STEP = 1;
    private Player player;
    private Point2D playerPosition;
    private Weapon weapon;
    private final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl());

    public BulletTest() {
        playerPosition = new Point2D(20,40);
        player = new Player(new BodyBuilder(), playerPosition, 100, this.gameField);
        weapon = player.get(Weapon.class).get();
    }

    @Test
    public void shootRight(){
        weapon.shoot(Direction.EAST);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        Point2D oldPosition = bullet.getBody().getPosition();

        int steps = weapon.getWeaponRange();
        for(int i = 0; i < steps; i++){
            //make the bullet move
            bullet.update(1);
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        //check the bullet new position

        Point2D newPosition = new Point2D(oldPosition.getX()+steps-INITIAL_STEP, oldPosition.getY());
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    @Test
    public void shootLeft(){
        weapon.shoot(Direction.WEST);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        Point2D oldPosition = bullet.getBody().getPosition();

        int steps = weapon.getWeaponRange();
        for(int i = 0; i < steps; i++){
            //make the bullet move
            bullet.update(1);
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        //check the bullet new position

        Point2D newPosition = new Point2D(oldPosition.getX()-steps+INITIAL_STEP, oldPosition.getY());
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    @Test
    public void shootUp(){
        weapon.shoot(Direction.NORTH);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        Point2D oldPosition = bullet.getBody().getPosition();

        int steps = weapon.getWeaponRange();
        for(int i = 0; i < steps; i++){
            //make the bullet move
            bullet.update(1);
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        //check the bullet new position

        Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY()+steps-INITIAL_STEP);
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    @Test
    public void shootDown(){
        weapon.shoot(Direction.SOUTH);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        Point2D oldPosition = bullet.getBody().getPosition();

        int steps = weapon.getWeaponRange();
        for(int i = 0; i < steps; i++){
            //make the bullet move
            bullet.update(1);
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        //check the bullet new position

        Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY()-steps+INITIAL_STEP);
        Assert.assertEquals(bullet.getBody().getPosition(), newPosition);
    }

    @Test
    public void shootWithWall(){
        //use only one direction because with the others it's the same

        int steps = weapon.getWeaponRange();

        //creation of a wall
        Point2D wallPosition = new Point2D(15, 40+steps/2+INITIAL_STEP);
        Dimension2D wallDimension = new Dimension2D(20, 3);
        Wall wall = new Wall(new BodyBuilder(), wallPosition, wallDimension);
        gameField.addEntity(wall);

        weapon.shoot(Direction.NORTH);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        Point2D oldPosition = bullet.getBody().getPosition();

        for(int i = 0; i < steps; i++){
            //make the bullet move
            bullet.update(1);
            if(!bullet.get(Life.class).isPresent()){
                break;
            }
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        //check the bullet new position

        Point2D newPosition = new Point2D(oldPosition.getX(), oldPosition.getY()+steps-INITIAL_STEP);
        Assert.assertNotEquals(bullet.getBody().getPosition(), newPosition);
    }
}
