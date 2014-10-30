package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Filters any signal changes outside of the provided range.
 * Created by Rimmer on 24/9/2014.
 */
public class Range<T extends Comparable> extends Signal<T> {
    public Range(final Signal<T> source, final T low, final T high) {
        super(source.get());
        source.onChange(value -> {
            boolean l = source.get().compareTo(low) > 0;
            boolean h = source.get().compareTo(high) < 0;
            if(l && h) {update(value);}
        });
    }
}
