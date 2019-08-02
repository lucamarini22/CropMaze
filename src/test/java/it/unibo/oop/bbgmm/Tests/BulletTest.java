package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BulletTest {
    private final Player player;
    private Point2D playerPosition;
    private final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl());

    public BulletTest() {
        playerPosition = new Point2D(20,40);
        player = new Player(new BodyBuilder(), playerPosition, 100, this.gameField);
    }

    @Test
    public void shootRight(){
        Weapon weapon = player.get(Weapon.class).get();
        weapon.shoot(Direction.EAST);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        Bullet bullet = list.get(0);
        for(int i = 0; i < weapon.getWeaponRange()-1; i++){
            //make the bullet move
            bullet.update(1);
        }
        player.destroy();
        bullet.destroy();

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        System.out.println("ciao");
    }
}
