package it.unibo.oop.bbgmm.Boundary;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Controls the sprite animation.
 *
 * From https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 */

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final double width;
    private final double height;

    private int lastIndex;

    /**
     * Creats a sprite animation
     * @param imageView
     *          The image view.
     * @param duration
     *          The duration.
     * @param count
     *          The count.
     * @param width
     *          The width.
     * @param height
     *          The height.
     */
    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, double width, double height) {
        this.imageView = imageView;
        this.count     = count;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        if(!getStatus().equals(Status.RUNNING)){
            return;
        }
        final int cols = (int) (imageView.getImage().getWidth() / width);
        final int rows = (int) (imageView.getImage().getHeight() / height);
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final double x = cols == 0 ? 0 : index % cols * width;
            final double y = rows == 0 ? 0 : index % rows * height;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}

