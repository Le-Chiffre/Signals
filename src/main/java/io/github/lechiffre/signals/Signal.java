package io.github.lechiffre.signals;

/**
 * Base class for normal signals that don't contain metadata.
 */
public class Signal<Type> extends GenericSignal<Type, Void> {
    /**
     * Creates a signal with the provided initial value.
     */
    public Signal(Type initial) {
        super(initial);
    }

    /**
     * Adds a listener that will be called when this signal changes.
     */
    public void onChange(Listener<Type> listener) {
        super.onChange(listener);
    }

    /**
     * Updates the current signal value.
     */
    public void update(Type value) {
        super.update(value, null);
    }
}
