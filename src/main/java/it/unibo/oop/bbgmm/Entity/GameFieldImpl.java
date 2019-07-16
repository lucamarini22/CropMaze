package it.unibo.oop.bbgmm.Entity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Runs the game field.
 */
public class GameFieldImpl implements GameField {


    private final Set<Entity> entities = new LinkedHashSet<>();

    @Override
    public void update(final double up) {
        //incompleted
        this.entities.stream()
                     .forEach(e -> e.update(up));
    }

    @Override
    public Entity addEntity(final Entity entity) {
        entities.add(entity);
        return entity;
    }

    private void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }
}
