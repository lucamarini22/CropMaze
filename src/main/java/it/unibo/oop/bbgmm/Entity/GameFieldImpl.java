package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Control.GameController;
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
    private final GameController gameController;
    private final PlayerStatistics playerStatistics;

    /**
     * {@link GameFieldImpl} constructor.
     * @param collisionSupervisor
     *      {@link CollisionSupervisor} instance
     * @param gameController
     *      {@link GameController} instance
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
        //Updates all the entities
        this.updateEntities(up);
        this.manageEntitiesToBeRemoved();
        //Searches for collisions
        this.collisionSupervisor.searchCollision();
        //If there are no enemies (aliens) it removes all the entities except the Player and it goes to the next level
        if (this.areAllEnemiesDead()) {
            this.entities.stream()
                         .filter(e -> !(e.getClass().equals(Player.class)))
                         .forEach(entitiesToBeRemoved::add);
            this.gameController.stop();
            this.gameController.triggerEndLevel();
        }
        //If there is not a Player, he's dead and it is a Game Over
        if (this.isPlayerDead()) {
            this.gameController.stop();
            this.gameController.triggerGameOver();
        }
    }

    @Override
    public Entity addEntity(final Entity entity) {
        entities.add(entity);
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.addCollisionComponent(entity.get(Collidable.class).get());
        }
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
        final Entity entity = event.getEntity();
        if (entity instanceof Alien) {
            this.playerStatistics.increaseKilledEnemies();
        }
        if (entity instanceof Coin) {
            this.playerStatistics.increaseCollectedMoney();
        }
        this.entitiesToBeRemoved.add(entity);
    }

    @Override
    public PlayerStatistics getPlayerStatistic() {
        return this.playerStatistics;
    }

    /**
     * @return a boolean that describes if the {@link Player} is dead or not
     */
    private boolean isPlayerDead() {
        return (int) this.entities.stream().filter(e -> e instanceof Player).count() == 0;
    }

    /**
     * @return a boolean that describes if all the enemies are dead or not
     */
    private boolean areAllEnemiesDead() {
        return (int) this.entities.stream().filter(e -> e instanceof Alien).count() == 0;
    }

    /**
     * Manages the entities to be removed.
     */
    private void manageEntitiesToBeRemoved() {
        this.entitiesToBeRemoved.forEach(e -> e.getDeathEvent().deregister(this::destroyEntity));
        this.entitiesToBeRemoved.forEach(this::removeEntity);
        this.entitiesToBeRemoved.clear();
    }

    /**
     * Updates the entities that should not be removed.
     * @param up
     *      Time to simulate, in seconds
     */
    private void updateEntities(final double up) {
        this.entities.stream()
                     .filter(e -> !entitiesToBeRemoved.contains(e))
                     .forEach(e -> e.update(up));
    }
}
