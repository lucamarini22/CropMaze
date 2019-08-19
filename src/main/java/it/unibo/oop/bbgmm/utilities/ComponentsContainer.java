package it.unibo.oop.bbgmm.utilities;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Models a interface container.
 * @param <T>
 *          The parent interface.
 */
public interface ComponentsContainer<T> {
    /**
     * Gets an element by its Interface.
     * Remember to work with interfaces and not concrete types!
     *
     * @param <C>
     *            the Interface type.
     * @param interfaceClass
     *            the interface class
     * @throws IllegalArgumentException
     *             if interf is not an interface
     * @return the element found
     */
    <C extends T> Optional<C> get(Class<C> interfaceClass) throws IllegalArgumentException;

    /**
     * Puts an element in the bag.
     *
     * @param element
     *            the element
     * @throws IllegalArgumentException
     *             - if the element or its interfaces are already present in the bag
     *             - if you try to insert an element implementing the parent interface only
     */
    void put(T element) throws IllegalArgumentException;

    /**
     * Removes an element by its type.
     *
     * @param <C>
     *            the type
     * @param type
     *            the type
     * @return The removed component
     */
    <C extends T> Optional<C> remove(Class<C> type);

    /**
     * Removes the element from the bag.
     *
     * @param element
     *            the element.
     */
    void remove(T element);

    /**
     * Clears the bag removing all elements.
     */
    void clear();

    /**
     * @return A stream of elements.
     */
    Stream<T> stream();


    /**
     * Convenience method.
     *
     * @param action
     *            an action to be executed on each element.
     */
    default void forEach(final Consumer<T> action) {
        stream().forEach(action);
    }

}
