package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * A combinator that compares the values of the provided signals
 * and returns the result as a signal.
 * Created by Rimmer on 24/9/2014.
 */
public class Compare<T extends Comparable<T>> extends Signal<Comparison> {
    public Compare(final Signal<T> a, final Signal<T> b) {
        super(compare(a.get(), b.get()));

        // If any source changes, we update the result.
        a.onChange(value -> onUpdate(value, b.get()));
        b.onChange(value -> onUpdate(a.get(), value));
    }

    private static <T extends Comparable> Comparison compare(T x, T y) {
        // This function needs a weird signature because of how Java generics are implemented.
        int res = x.compareTo(y);
        if(res == 0) return Comparison.Equal;
        else if(res > 0) return Comparison.Greater;
        else return Comparison.Lower;
    }

    private void onUpdate(T x, T y) {
        update(compare(x, y));
    }
}
