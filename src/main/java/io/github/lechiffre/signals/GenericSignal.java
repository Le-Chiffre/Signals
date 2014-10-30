package io.github.lechiffre.signals;

import java.util.HashSet;
import java.util.Set;

/**
 * Base class for generic signals that contain metadata.
 * Signal is derived from this.
 */
public class GenericSignal<Type, Meta> {
    private Type cache;
    private Set<GenericListener<Type, Meta>> listeners = new HashSet<>();

    /**
     * Creates a signal with the provided initial value.
     */
    public GenericSignal(Type initial) {
        cache = initial;
    }

    /**
     * Adds a listener that will be called when this signal changes.
     */
    public void onChange(GenericListener<Type, Meta> listener) {
        listeners.add(listener);
    }

    /**
     * Returns the last known value of this signal.
     */
    public Type get() {
        return cache;
    }

    /**
     * Updates the current signal value.
     */
    public void update(Type value, Meta meta) {
        cache = value;
        for(GenericListener l : listeners) {
            l.change(value, meta);
        }
    }

    /**
     * Changes the current signal value without triggering an update.
     * This should only be used by some special filters.
     */
    protected void updateSilent(Type value) {
        cache = value;
    }
}
