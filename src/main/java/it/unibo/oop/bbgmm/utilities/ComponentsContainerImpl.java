package it.unibo.oop.bbgmm.utilities;


import com.google.common.reflect.TypeToken;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Interfaces container made for components.
 * @param <T>
 *     The parent interface
 */

public final class ComponentsContainerImpl<T> implements ComponentsContainer<T> {
    private final Map<Class<?>, T> elements = new ConcurrentHashMap<>();
    private final Class<T> interfaceParent;

    /**
     * Class constructor.
     * @param interfaceParent
     *      The parent interface
     */
    public ComponentsContainerImpl(final Class<T> interfaceParent) {
        this.isInterface(interfaceParent);
        this.interfaceParent = interfaceParent;
    }
    @Override
    public <C extends T> Optional<C> get(final Class<C> interfaceClass) throws IllegalArgumentException {
        this.isInterface(interfaceClass);
        return Optional.ofNullable(interfaceClass.cast(this.elements.get(interfaceClass)));
    }

    @Override
    public void put(final T element) throws IllegalArgumentException {
        final Set<Class<?>> interfaces = allInterfaces(element.getClass());
        if (interfaces.isEmpty()) {
            throw new IllegalArgumentException("Element does not implement any suitable child interface");
        }

        interfaces.stream()
                .filter(in -> this.elements.get(in) != null)
                .findAny()
                .ifPresent(in -> {
                    throw new IllegalArgumentException(
                            "Element implemented interface " + in.getSimpleName() + " already present in the bag.");
                });

        interfaces.forEach(in -> this.elements.put(in, element));
    }

    @Override
    public void remove(final T element) {
        this.elements.values().remove(element);
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public <C extends T> Optional<C> remove(final Class<C> type) {
        return Optional.ofNullable(type.cast(this.elements.remove(type)));
    }

    private void isInterface(final Class<?> interf) {
        if (!interf.isInterface()) {
            throw new IllegalArgumentException("Not a interface");
        }
    }

    @Override
    public Stream<T> stream() {
        return elements.values().stream();
    }

    private Set<Class<?>> allInterfaces(final Class<?> type) {

        return TypeToken.of(type).getTypes().interfaces()
                .stream()
                .map(TypeToken::getRawType)
                .filter(t -> !t.equals(this.interfaceParent))
                .filter(this.interfaceParent::isAssignableFrom)
                .collect(Collectors.toSet());
    }
}
