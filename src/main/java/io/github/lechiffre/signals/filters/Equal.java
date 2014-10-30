package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * A filter that has the value true when the two input signals are equal.
 */
public class Equal<T extends Comparable<T>> extends Signal<Boolean> {
    public Equal(final Signal<T> a, final Signal<T> b) {
        super(compare(a.get(), b.get()));

        // If any source changes, we update the result.
        a.onChange(value -> onUpdate(value, b.get()));
        b.onChange(value -> onUpdate(a.get(), value));
    }

    private static <T extends Comparable> boolean compare(T x, T y) {
        return x.compareTo(y) == 0;
    }

    private void onUpdate(T x, T y) {
        update(compare(x, y));
    }
}
