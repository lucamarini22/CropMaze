package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.GameField;
import it.unibo.oop.bbgmm.Entity.Player;
import javafx.geometry.Point2D;
import org.junit.Test;

import java.util.Set;

public class Distruction {

    @Test
    public void testDestroy(){
        Player p = new Player(new BodyBuilder(), Point2D.ZERO, 100, new GameField() {
            @Override
            public void update(double up) {

            }

            @Override
            public Entity addEntity(Entity entity) {
                return null;
            }

            @Override
            public Set<Entity> getEntities() {
                return null;
            }

            @Override
            public void removeEntity(Entity entity) {

            }

            @Override
            public Set<Entity> getWalls() {
                return null;
            }
        });
        p.destroy();
    }
}



