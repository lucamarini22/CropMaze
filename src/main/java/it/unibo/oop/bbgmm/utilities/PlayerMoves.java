package it.unibo.oop.bbgmm.utilities;

/**
 * All possible distance vector.
 */
public enum PlayerMoves {
    /**
     * Up distance vector.
     */
    UP(0, 1),
    /**
     * Down distance vector.
     */
    DOWN(0, -1),
    /**
     * Left distance vector.
     */
    LEFT(-1, 0),
    /**
     * Right distance vector.
     */
    RIGHT(1, 0);

    private double x;
    private double y;

    PlayerMoves(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x value.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The y value.
     */
    public double getY() {
        return y;
    }
}
