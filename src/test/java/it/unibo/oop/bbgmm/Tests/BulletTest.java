package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BulletTest {
    private final EntityFactory entityFactory;
    private final Player player;
    private Point2D playerPosition;

    public BulletTest() {
        entityFactory = new EntityFactoryImpl();
        playerPosition = new Point2D(20,40);
        player = entityFactory.createPlayer(playerPosition);
    }

    @Test
    public void shootRight(){
        Weapon weapon = player.get(Weapon.class).get();
        weapon.shoot(Direction.EAST);
        List<Bullet> list = weapon.getBulletList();

        //list size should be 1
        Assert.assertEquals(list.size(),1);

        for(int i = 0; i < weapon.getWeaponRange(); i++){
            //make the bullet move
            list.get(0).update(1);
        }

        list = weapon.getBulletList();

        //list size should be 0
        Assert.assertEquals(list.size(),0);

        System.out.println("ciao");
    }
}
