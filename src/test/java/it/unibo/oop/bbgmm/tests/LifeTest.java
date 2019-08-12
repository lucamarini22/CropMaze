package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.entity.component.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {
    private static final int LIFEPOINTS = 100;
    private static final int DAMAGE = 10;
    private final Life life = new LifeComponent(LIFEPOINTS);
    private final Damage damage = new DamageComponent(DAMAGE);
    private final BodyBuilder body = new BodyBuilder();

    /**
     * A simple test for the life getting damaged
     */
    @Test
    public void lifeDamagedTest(){
        life.damaged(damage.getDamage());
        assertEquals(90,life.getCurrentLifePoints());
        life.damaged(damage.getDamage()+13);
        assertEquals(67,life.getCurrentLifePoints());
    }

    /**
     * A simple test for life getting healed, life should not go above LIFEPOINTS
     */
    @Test
    public void lifeHealingTest(){
        life.incrementLife(33);
        assertEquals(LIFEPOINTS,life.getCurrentLifePoints());
        life.incrementLife(10);
        assertEquals(LIFEPOINTS,life.getCurrentLifePoints());
    }

    /**
     *  Life can't be damaged if invulnerable (vulnerable = false)
     */
    @Test
    public void invulnerableTest(){
        life.setVulnerability(true);
        life.damaged(damage.getDamage());
        assertEquals(90,life.getCurrentLifePoints());
        life.setVulnerability(false);
        life.damaged(damage.getDamage());
        assertEquals(90,life.getCurrentLifePoints());
        life.setVulnerability(true);
        life.damaged(damage.getDamage());
        assertEquals(80,life.getCurrentLifePoints());
    }

    /**
     * Test if the entity if alive or not after getting a lot of damage
     */
    @Test
    public void aliveTest(){
        assertTrue(life.isAlive());
        for(int i = 0 ;i < 10; i++){
            life.damaged(damage.getDamage());
        }
        assertFalse(life.isAlive());
    }

    /**
     * Lifepoints should not go under 0
     */
    @Test
    public void underRangeTest(){
        for(int i = 0 ;i < 11; i++){
            life.damaged(damage.getDamage());
        }
        assertEquals(0,life.getCurrentLifePoints());
    }







}