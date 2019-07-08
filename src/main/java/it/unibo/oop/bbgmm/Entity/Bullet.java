package it.unibo.oop.bbgmm.Entity;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

//E' un'entità
public class Bullet extends AbstractMovement {

    private final int range;
    private final int damage;
    private final Direction direction;
    private Image bulletImage;
    private final Rectangle shape;

    public Bullet(Direction ownerDirection, int weaponRange, int weaponDamage/*,Position pos*/) {
        this.range = weaponRange;
        this.damage = weaponDamage;
        this.direction = ownerDirection;

        updateState();
        chooseImage();

        this.shape = new Rectangle(/*pos.X,pos.Y,*/bulletImage.getWidth(),bulletImage.getHeight());
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

    }
}
