package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Control.GameController;
import it.unibo.oop.bbgmm.Control.Level;
import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
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
    private final Set<Entity> entitiesToBeRemoved;
    private Entity player;
    private Level level;
    private GameController gameController;
    private final PlayerStatistics playerStatistics;

    /**
     * {@link GameFieldImpl} constructor.
     * @param collisionSupervisor
     *      {@link CollisionSupervisor} instance
     */
    public GameFieldImpl(final CollisionSupervisor collisionSupervisor, final GameController gameController) {
        this.entities =  new LinkedHashSet<>();
        this.collisionSupervisor = collisionSupervisor;
        this.entitiesToBeRemoved  = new LinkedHashSet<>();
        this.gameController = gameController;
        this.playerStatistics = new PlayerStatisticsImpl();
    }

    @Override
    public void update(final double up) {
        this.entities.stream()
                .filter(e -> !entitiesToBeRemoved.contains(e))
                .forEach(e -> e.update(up));
        this.entitiesToBeRemoved.forEach(e -> e.getDeathEvent().deregister(this::destroyEntity));
        this.entitiesToBeRemoved.forEach(this::removeEntity);
        this.entitiesToBeRemoved.clear();
        this.collisionSupervisor.searchCollision();
        if ((int) this.entities.stream().filter(e -> e instanceof Alien).count() == 0) {
            this.gameController.stop();
            this.gameController.triggerEndLevel();
        }
       /* if (this.entities.size() == 1) {
            this.gameController.stop();
            this.gameController.triggerEndLevel();
        }*/
    }

    @Override
    public Entity addEntity(final Entity entity) {
        entities.add(entity);
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.addCollisionComponent(entity.get(Collidable.class).get());
        }
        System.out.println(entity);
        entity.getDeathEvent().register(this::destroyEntity);
        return entity;
    }

    @Override
    public Set<Entity> getEntities() {
        return Collections.unmodifiableSet(this.entities);
    }

    @Override
    public void removeEntity(final Entity entity) {
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.removeCollisionComponent(entity.get(Collidable.class).get());
        }
        this.entities.remove(entity);
        entity.destroy();
    }

    @Override
    public Set<Entity> getWalls() {
        return this.entities.stream().filter(e -> e instanceof Wall).collect(Collectors.toSet());
    }

    @Override
    public void destroyEntity(final DeathEvent event) {
        //if an Alien is killed, it controls the number of alive Aliens, and if it is one (the last Alien killed),
        // then the next level has to start
        Entity entity = event.getEntity();

        if (entity instanceof Alien) {
            this.playerStatistics.increaseKilledEnemies();
            if (this.getAliveEnemies() == 1) {
                this.entities.stream().filter(e -> !(e.getClass().equals(Player.class)))
                        .forEach(entitiesToBeRemoved::add);
                return;
            }
        }
        if (entity instanceof Coin) {
            this.playerStatistics.increaseCollectedMoney();
        }
        this.entitiesToBeRemoved.add(entity);
    }

    @Override
    public void setLevel(final Level level) {
        this.level = level;
    }

    @Override
    public PlayerStatistics getPlayerStatistic() {
        return this.playerStatistics;
    }

    /**
     * @return the number of alive enemies
     */
    private int getAliveEnemies() {
        return (int) this.entities.stream().filter(e -> e instanceof Alien).count();
    }
}
