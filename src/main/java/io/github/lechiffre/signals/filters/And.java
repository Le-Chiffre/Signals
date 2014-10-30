package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Implements an AND gate for boolean signals.
 */
public class And extends Signal<Boolean> {
    public And(final Signal<Boolean> a) {
        this(a,a);
    }

    public And(final Signal<Boolean> a, final Signal<Boolean> b) {
        super(a.get() && b.get());
        a.onChange(value -> update(value && b.get()));
        b.onChange(value -> update(a.get() && value));
    }
}
