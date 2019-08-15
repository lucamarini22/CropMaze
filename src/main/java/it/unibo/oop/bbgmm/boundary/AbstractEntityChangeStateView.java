package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.EntityState;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to manage entity state chang.
 * @param <S>
 *      entity state type
 */
public abstract class AbstractEntityChangeStateView<S extends EntityState> extends AbstractEntityView implements EntityChangeState<S> {
    private final Map<S, Runnable> animations = new HashMap<>();
    private Animation currentAnimation;

    /**
     * @param group
     *      The group in which the entity view is added.
     * @param dimension
     *      The entity view dimension.
     */
    public AbstractEntityChangeStateView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
        this.currentAnimation = new Transition() {
            @Override
            protected void interpolate(final double frac) {
                //it's an empty animation
            }
        };
    }

    /**
     * Add the animation with the relative state into the animation map.
     * @param state
     *      The entity state.
     * @param animation
     *      The state animation.
     */
    public void putAnimation(final S state, final Runnable animation) {
        this.animations.put(state, animation);
    }

    /**
     * The current animation is stopped.
     */
    @Override
    public void removeFromView() {
        currentAnimation.stop();
        super.removeFromView();
    }

    /**
     * Used to make a looping sprite animation.
     *
     * @param image
     *            The sprite image.
     * @param duration
     *            The duration of a frame.
     * @param frames
     *            The number of frames used for the animation.
     * @return A runnable instance animation.
     */
    public Runnable dynamicAnimation(final Image image, final Duration duration, final int frames) {
        return () -> {
            setImage(image);
            final Animation animation = new SpriteAnimation(getView(), duration, frames,
                    getDimension().getWidth(), getDimension().getHeight());
            animation.setCycleCount(Animation.INDEFINITE);
            setAnimation(animation);
        };
    }
    /**
     * Used when the entity is not moving.
     * @param image
     *      The entity image.
     * @return
     *      A runnable animation.
     */
    public Runnable staticAnimation(final Image image) {
        return () -> setImage(image);
    }
    /**
     * Sets the entity image and the portion that will be shown in the game.
     * @param image
     *      The entity image.
     */
    public void setImage(final Image image) {
        getView().setImage(image);
        getView().setFitWidth(this.getDimension().getWidth());
        getView().setPreserveRatio(true);
        getView().setViewport(new Rectangle2D(0, 0, this.getDimension().getWidth(), this.getDimension().getHeight()));
    }

    /**
     * Set the animation for the entity view.
     * @param newAnimation
     *      The animation instance to set the current animation.
     */
    public void setAnimation(final Animation newAnimation) {
        this.currentAnimation.stop();
        this.currentAnimation = newAnimation;
        this.currentAnimation.play();
    }

    /**
     * Used to start an animation from the mapped animations.
     *
     * @param state
     *            The entity state instance associated to the specific animation that has to start.
     */
    protected void startAnimation(final S state) {
        currentAnimation.stop();
        animations.get(state).run();
    }

    /**
     * Run the animation based on the state.
     * @param state
     *      The animation state
     */
    @Override
    public void changeState(final S state) {
        if (this.animations.containsKey(state)) {
            this.animations.get(state).run();
        }
    }
}
