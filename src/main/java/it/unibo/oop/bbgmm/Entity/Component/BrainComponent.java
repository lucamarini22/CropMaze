package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractMovement;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Point2D;

import java.awt.*;
import java.util.Set;

public class BrainComponent extends AbstractEntityComponent implements Brain {


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

        return false;

    }

    @Override
    public void checkDirection(Entity player, Entity alien) {
        if(!findPlayer(player, alien)){

            if(player.getBody().getPosition().getY() < alien.getBody().getPosition().getY()){
                alien.get(Feet)
            }
        }

    }

    @Override
    public void update(double delta) {

    }

}
