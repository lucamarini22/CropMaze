package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisor;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Runs the game field.
 */
public final class GameFieldImpl implements GameField {
    private final Set<Entity> entities;
    private final CollisionSupervisor collisionSupervisor;

    GameFieldImpl(final CollisionSupervisor collisionSupervisor) {
        this.entities =  new LinkedHashSet<>();
        this.collisionSupervisor = collisionSupervisor;
    }

    @Override
    public void update(final double up) {
        //incompleted
        this.entities.stream()
                     .forEach(e -> e.update(up));
        this.collisionSupervisor.searchCollision();
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
