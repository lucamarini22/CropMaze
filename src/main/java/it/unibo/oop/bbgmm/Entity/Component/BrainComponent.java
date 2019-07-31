package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractMovement;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;

public class BrainComponent extends AbstractMovement implements Brain {

    private double movementSpeed;

    public BrainComponent(double speed){
        super(speed);
        this.movementSpeed = speed;
    }
    @Override
    public void moveToPlayer(Entity player, Entity alien) {
        if(player.getBody().getPosition().getX() > alien.getBody().getPosition().getX()){
            alien.getBody().getPosition().add(1,0);
            setDesiredDirection(Direction.SOUTH);
            move(Direction.SOUTH, movementSpeed);
        }
        else{
            alien.getBody().getPosition().add(-1,0);
            setDesiredDirection(Direction.NORTH);
            move(Direction.NORTH, movementSpeed);
        }

        if(player.getBody().getPosition().getY() > alien.getBody().getPosition().getY()){
            alien.getBody().getPosition().add(0, 1);
            setDesiredDirection(Direction.EAST);
            move(Direction.EAST, movementSpeed);
        }
        else{
            alien.getBody().getPosition().add(0, -1);
            setDesiredDirection(Direction.WEST);
            move(Direction.WEST, movementSpeed);
        }

    }

    @Override
    public boolean findPlayer(Entity player, Entity alien) {

        double alienX = alien.getBody().getPosition().getX();
        double alienY = alien.getBody().getPosition().getY();

        if(player.getBody().getPosition().getX() == (alienX - 1) || player.getBody().getPosition().getX() == (alienX+1)){

            if (player.getBody().getPosition().getY() == (alienY - 1 ) || player.getBody().getPosition().getY() == (alienY + 1)){
                return true;
            }

            else{
                return false;
            }
        }

        else {return false;}
    }

    @Override
    public void update(double delta) {

    }
}
