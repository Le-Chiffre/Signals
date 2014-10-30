package io.github.lechiffre.signals;

/**
 * A signal that always has the same value.
 * Created by Rimmer on 26/9/2014.
 */
public class Constant<T> extends Signal<T> {
    public Constant(T value) {
        super(value);
    }
}
