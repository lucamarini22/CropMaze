package it.unibo.oop.bbgmm.Entity;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Runs the game field.
 */
public final class GameFieldImpl implements GameField {


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

    @Override
    public Set<Entity> getEntities() {
        return Collections.unmodifiableSet(this.entities);
    }

    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public Set<Entity> getWalls() {
        return this.entities.stream().filter(e -> e.getClass().equals(Wall.class)).collect(Collectors.toSet());
    }


}
