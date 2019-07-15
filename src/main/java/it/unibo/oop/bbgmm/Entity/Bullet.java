package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bullet extends AbstractMovement {
// it is considered as an entity, but it doesn't implement interface "Entity" because it has not to
// implement its methods

    private final int range; //necessary to deal with collisions
    private final int damage;
    private final Direction direction;
    private Image bulletImage;
    private final Rectangle shape;
    private final Point2D position;

    public Bullet(Direction ownerDirection, int weaponRange, int weaponDamage, Point2D position) {
        this.range = weaponRange;
        this.damage = weaponDamage;
        this.direction = ownerDirection;
        this.position = position;

        updateState();
        chooseImage();

        this.shape = new Rectangle(position.getX(),position.getY(),bulletImage.getWidth(),bulletImage.getHeight());
    }

    /**
     * Method used to set the image of the bullet based on the direction
     */
    private void chooseImage() {
        double width = 1.5; //andrà sostituito con i valori giusti
        double height = 1.5;
        switch (direction){
            case NORTH: bulletImage = new Image("images/bullets/BulletUp.png",width,height,true,true);
                break;
            case SOUTH: bulletImage = new Image("images/bullets/BulletDown.png",width,height,true,true);
                break;
            case EAST: bulletImage = new Image("images/bullets/BulletRigth.png",width,height,true,true);
                break;
            case WEST: bulletImage = new Image("images/bullets/BulletLeft.png",width,height,true,true);
                break;
        }
    }

    /**
     * Method called to update the State of the player
     */
    private void updateState(){
        setState(State.SHOOTING);
    }


    /**
     * Sychronizes the component
     *
     * @param delta The time passed since the last call in seconds
     */
    @Override
    public void update(double delta) {

    }

    /**
     * Getter for the image of the Bullet
     *
     * @return bulletImage
     */
    public Image getImage() {
        return bulletImage;
    }

    /**
     * Method called to update the position
     */
    private void move(){
        //it will update the position of the shape
        switch (direction){
            case NORTH: this.shape.setY(this.shape.getY()/*+1*/);
                break;
            case SOUTH: this.shape.setY(this.shape.getY()/*-1*/);
                break;
            case EAST: this.shape.setX(this.shape.getX()/*+1*/);
                break;
            case WEST: this.shape.setX(this.shape.getX()/*-1*/);
                break;
        }
    }
}
