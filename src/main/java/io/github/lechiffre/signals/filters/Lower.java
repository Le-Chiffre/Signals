package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * A filter that has the value true when a < b.
 */
public class Lower<T extends Comparable<T>> extends Signal<Boolean> {
    public Lower(final Signal<T> a, final Signal<T> b) {
        super(compare(a.get(), b.get()));

        // If any source changes, we update the result.
        a.onChange(value -> onUpdate(value, b.get()));
        b.onChange(value -> onUpdate(a.get(), value));
    }

    private static <T extends Comparable> boolean compare(T x, T y) {
        return x.compareTo(y) < 0;
    }

    private void onUpdate(T x, T y) {
        update(compare(x, y));
    }
}
