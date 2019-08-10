package it.unibo.oop.bbgmm.boundary;

/**
 * Interface for controlling a view.
 * @param <T>
 *     Type of the observer (the controller of the view)
 */
public interface ObservableView<T> {
    /**
     * Sets the observer.
     * @param observer
     *      Observer to set
     */
    void setObserver(T observer);
}
