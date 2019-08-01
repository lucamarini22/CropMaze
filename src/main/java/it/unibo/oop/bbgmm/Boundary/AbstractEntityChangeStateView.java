package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.EntityState;
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
 * Class to manage entity state chang
 * @param <S>
 *      Entity state type
 */
public abstract class AbstractEntityChangeStateView<S extends EntityState> extends AbstractEntityView implements EntityChangeState<S> {
    private final Map<S, Runnable> animations = new HashMap<>();
    private Animation currentAnimation;

    public AbstractEntityChangeStateView(final Group group, final Dimension2D dimension){
        super(group,dimension);
        this.currentAnimation = new Transition() {
            @Override
            protected void interpolate(double frac) {
                //it's an empty animation
            }
        };
    }

    /**
     * Add the animation with the relative state into the animation map
     * @param state
     *      The entity state
     * @param animation
     *      The state animation
     */
    public void putAnimation(final S state, final Runnable animation){
        this.animations.put(state,animation);
    }

    public Runnable dynamicAnimation(final Image image, final Duration duration, final int frames){
        return () -> {
            setImage(image);
            final Animation animation = new SpriteAnimation(getView(),duration,frames
                    ,getDimension().getWidth(),getDimension().getHeight());
            animation.setCycleCount(Animation.INDEFINITE);
            setAnimation(animation);
        };
    }
    /**
     * Used when the entity is not moving
     * @param image
     *      The entity image
     * @return
     *      A runnable animation
     */
    public Runnable staticAnimation(final Image image){
        return () -> setImage(image);
    }
    /**
     * Sets the entity image and the portion that will be shown in the game
     * @param image
     *      The entity image
     */
    public void setImage(final Image image){
        getView().setImage(image);
        getView().setViewport(new Rectangle2D(0,0,this.getDimension().getWidth(),this.getDimension().getHeight()));
    }

    /**
     * Set the animation for the entity view
     * @param newAnimation
     *      The animation instance to set the current animation
     */
    public void setAnimation(final Animation newAnimation){
        this.currentAnimation.stop();
        this.currentAnimation = newAnimation;
        this.currentAnimation.play();
    }

    @Override
    public void changeState(S state) {
        if(this.animations.containsKey(state)){
            this.animations.get(state).run();
        }
    }
}