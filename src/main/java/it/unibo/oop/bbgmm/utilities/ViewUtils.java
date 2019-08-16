package it.unibo.oop.bbgmm.utilities;
import javafx.geometry.Point2D;

/**
 * Utility methods for the view.
 */
public final class ViewUtils {

    private static final int PIXELS_PER_METER = 25;

    private ViewUtils() {

    }

    /**
     * @param meters
     *          Meters to be converted.
     * @return meters in pixels.
     */
    public static double metersToPixels(final double meters) {
        return meters * PIXELS_PER_METER;
    }

    /**
     * @param point
     *          Points to be converted in FX coordinates.
     * @return point in FX coordinates.
     */
    public static Point2D worldPointToFX(final Point2D point) {
        return invertY(point.multiply(PIXELS_PER_METER));
    }

    /**
     * 
     * @param p
     *          Point to be inverted 
     * @return inverted point
     */
    private static Point2D invertY(final Point2D p) {
        return new Point2D(p.getX(), -p.getY());
    }
}
