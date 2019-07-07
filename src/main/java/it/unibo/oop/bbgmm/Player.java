package it.unibo.oop.bbgmm;

public class Player implements Statistics {

    private int speed;
    private int life;
    private int ownedCoins = 0;
    private Attack attack;

    Player(int life, int speed, Attack attack) {
        this.life = life;
        this.speed = speed;
        this.attack = attack;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public Attack getAttack() {
        return this.attack;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Adds to the owned coins the amount of coins the player collects.
     * @param coins
     *              Coins collected.
     */
    public void setCoins(int coins){
        this.ownedCoins += coins;
    }

    /**
     * @return
     *          Coins owned by the player.
     */
    public int getCoins() {
        return this.ownedCoins;
    }

    /**
     * @return
     *          True if the player have more than 0 life points. False otherwise.
     */
    public boolean isAlive() {
        return this.life>0;
    }


}
