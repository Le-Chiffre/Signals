package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Implements a NAND gate for boolean signals.
 */
public class Nand extends Signal<Boolean> {
    public Nand(final Signal<Boolean> a) {
        this(a,a);
    }

    public Nand(final Signal<Boolean> a, final Signal<Boolean> b) {
        super(!(a.get() && b.get()));
        a.onChange(value -> update(!(value && b.get())));
        b.onChange(value -> update(!(a.get() && value)));
    }
}
