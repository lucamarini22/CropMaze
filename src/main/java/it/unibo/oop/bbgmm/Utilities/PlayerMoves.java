package it.unibo.oop.bbgmm.Utilities;

public enum PlayerMoves {
    UP(0,1),
    DOWN(0,-1),
    LEFT(-1,0),
    RIGHT(1,0);

    public double x;
    public double y;

    PlayerMoves(final double x, final double y){
        this.x = x;
        this.y = y;
    }
}
