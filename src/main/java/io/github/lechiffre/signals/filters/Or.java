package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Implements an OR gate for boolean signals.
 */
public class Or extends Signal<Boolean> {
    public Or(final Signal<Boolean> a) {
        this(a,a);
    }

    public Or(final Signal<Boolean> a, final Signal<Boolean> b) {
        super(a.get() || b.get());
        a.onChange(value -> update(value && b.get()));
        b.onChange(value -> update(a.get() && value));
    }
}
