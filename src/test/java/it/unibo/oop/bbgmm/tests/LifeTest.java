package it.unibo.oop.bbgmm.tests;


import it.unibo.oop.bbgmm.entity.component.Damage;
import it.unibo.oop.bbgmm.entity.component.DamageComponent;
import it.unibo.oop.bbgmm.entity.component.Life;
import it.unibo.oop.bbgmm.entity.component.LifeComponent;
import org.junit.Test;
import org.junit.Assert;

public class LifeTest {
    private static final int LIFEPOINTS = 100;
    private static final int DAMAGE = 10;
    private final Life life = new LifeComponent(LIFEPOINTS);
    private final Damage damage = new DamageComponent(DAMAGE);

    /**
     * A simple test for the life getting damaged
     */
    @Test
    public void lifeDamagedTest(){
        life.damaged(damage.getDamage());
        Assert.assertEquals(90,life.getCurrentLifePoints());
        life.damaged(damage.getDamage()+13);
        Assert.assertEquals(67,life.getCurrentLifePoints());
    }

    /**
     * A simple test for life getting healed, life should not go above LIFEPOINTS
     */
    @Test
    public void lifeHealingTest(){
        life.incrementLife(33);
        Assert.assertEquals(LIFEPOINTS,life.getCurrentLifePoints());
        life.incrementLife(10);
        Assert.assertEquals(LIFEPOINTS,life.getCurrentLifePoints());
    }

    /**
     *  Life can't be damaged if invulnerable (vulnerable = false)
     */
    @Test
    public void invulnerableTest(){
        life.setVulnerability(true);
        life.damaged(damage.getDamage());
        Assert.assertEquals(90,life.getCurrentLifePoints());
        life.setVulnerability(false);
        life.damaged(damage.getDamage());
        Assert.assertEquals(90,life.getCurrentLifePoints());
        life.setVulnerability(true);
        life.damaged(damage.getDamage());
        Assert.assertEquals(80,life.getCurrentLifePoints());
    }

    /**
     * Test if the entity if alive or not after getting a lot of damage
     */
    @Test
    public void aliveTest(){
        Assert.assertTrue(life.isAlive());
        for(int i = 0 ;i < 10; i++){
            life.damaged(damage.getDamage());
        }
        Assert.assertFalse(life.isAlive());
    }

    /**
     * Lifepoints should not go under 0
     */
    @Test
    public void underRangeTest(){
        for(int i = 0 ;i < 11; i++){
            life.damaged(damage.getDamage());
        }
        Assert.assertEquals(0,life.getCurrentLifePoints());
    }







}