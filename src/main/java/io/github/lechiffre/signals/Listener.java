package io.github.lechiffre.signals;

/**
 * Listener interface for normal signals that don't contain metadata.
 */
public interface Listener<Type> extends GenericListener<Type, Void> {
    public default void change(Type value, Void meta) {change(value);}
    public void change(Type value);
}
