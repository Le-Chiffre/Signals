package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Works like a transistor; signal changes are only forwarded if the predicate is true.
 * Created by Rimmer on 24/9/2014.
 */
public class Predicate<T> extends Signal<T> {
    public Predicate(Signal<T> signal, final Signal<Boolean> predicate) {
        super(signal.get());
        signal.onChange(value -> {if(predicate.get()) {update(value);}});
    }

    /** Special constructor for Precicate<Void> that only takes the predicate parameter. */
    public Predicate(final Signal<Boolean> predicate) {
        super(null);
        predicate.onChange(value -> {if(value) {update(null);}});
    }
}
