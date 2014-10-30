package io.github.lechiffre.signals;

/**
 * Listener interface for generic signals that contain metadata.
 */
public interface GenericListener<Type, Meta> {
    void change(Type value, Meta meta);
}
