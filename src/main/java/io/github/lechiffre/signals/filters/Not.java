package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Implements a NOT gate for boolean signals.
 */
public class Not extends Signal<Boolean> {
    public Not(final Signal<Boolean> a) {
        super(!a.get());
        a.onChange(value -> update(!value));
    }
}
