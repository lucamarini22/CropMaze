package it.unibo.oop.bbgmm.utilities;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Util class for streams.
 */
public final class StreamsUtil {
    /**
     * Utility class for streams.
     */
    private StreamsUtil() {
    }

    /**
     * Generate a {@link Stream} from an {@link Iterable}.
     * @param it
     *          the {@link Iterable}
     * @param <T>
     *          Generic
     * @return the {@link Stream}
     */

    public static <T> Stream<T> stream(final Iterable<T> it) {
        return StreamSupport.stream(it.spliterator(), false);
    }

    /**
     * Generate a {@link Stream} from an {@link Iterator}.
     * @param it
     *          the {@link Iterator}
     * @param <T>
     *          Generic
     * @return the stream.
     */
    public static <T> Stream<T> stream(final Iterator<T> it) {
        return stream(() -> it);
    }
}
