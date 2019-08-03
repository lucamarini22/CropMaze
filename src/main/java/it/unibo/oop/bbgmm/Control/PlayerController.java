package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.BulletView;
import it.unibo.oop.bbgmm.Boundary.PlayerView;
import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Component.Bag;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Movement;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController extends AliveEntityController implements PlayerInputListener {

    private final PlayerView playerView;
    private final List<BulletController> bulletControllers;
    private final List<BulletController> removedControllers;

    public PlayerController(final Entity player, final PlayerView playerView){
        super(player,playerView);
        this.playerView = playerView;
        this.bulletControllers = new ArrayList<>();
        this.removedControllers = new ArrayList<>();
    }

    @Override
    public void lifeChange(final Life life) {
        super.lifeChange(life);
    }

    @Override
    public void move(final Point2D vector) {
        getEntity().get(Movement.class).ifPresent(movement -> movement.move(vector));
        System.out.println(" move in player controller " + getEntity().get(Movement.class).get().getState());
        movementChanged(getEntity().get(Movement.class).get());

    }

    private void updateLifeView(){
        getEntity().get(Life.class).ifPresent(life ->{
            playerView.setMaxLifePoints(life.getMaxLifePoints());
            playerView.setCurrentLifePoints(life.getCurrentLifePoints());
                });
    }

    @Override
    public void shoot(Point2D vector) {
        /*getEntity().get(Weapon.class).ifPresent(weapon -> {
            weapon.shoot(this.calculateDirection(vector));
        });*/
        if(getEntity().get(Weapon.class).isPresent()){
            Direction direction = this.calculateDirection(vector);
            Optional<Bullet> bullet = getEntity().get(Weapon.class)
                                                 .get()
                                                 .shoot(direction);
            bullet.ifPresent(b -> createBulletController(b, direction));
        }


    }

    private void createBulletController(Bullet bullet, Direction direction){
        BulletController controller = new BulletController(bullet, new BulletView(playerView.getGroup(), direction));
        bulletControllers.add(controller);
    }

    private Direction calculateDirection(Point2D vector){
        if(vector.getY() != vector.getX() ){
            if(vector.getX() == 0 && vector.getY() > 0){
                return Direction.NORTH;
            }
            if(vector.getX() == 0 && vector.getY() < 0){
                return Direction.SOUTH;
            }
            if(vector.getX() < 0 && vector.getY() == 0){
                return Direction.WEST;
            }
            if(vector.getX() > 0 && vector.getY() == 0){
                return Direction.EAST;
            }
        }
        return Direction.NOTHING;
    }

    private void updateCoins(){
        getEntity().get(Bag.class).ifPresent(bag ->
                playerView.setCoins(bag.getMoney()));
    }

    @Override
    public void update(){
        super.update();
        bulletControllers.stream()
                .filter(c -> !removedControllers.contains(c))
                .forEach(EntityController::update);
        removedControllers.forEach(c -> bulletControllers.remove(c));
        removedControllers.clear();
    }
}
