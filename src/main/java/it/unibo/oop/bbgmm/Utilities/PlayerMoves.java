package it.unibo.oop.bbgmm.Utilities;

public enum PlayerMoves {
    UP(1,0),
    DOWN(-1,0),
    LEFT(0,-1),
    RIGHT(0,1);

    public int x;
    public int y;

    PlayerMoves(final int x, final int y){
        this.x = x;
        this.y = y;
    }
}
