package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;
import java.util.Collection;

/**
 * Folds the provided collection into a single value, using the provided function.
 */
public class Fold<T, U> extends Signal<U> {
    public interface Folder<T, U> {
        U fold(U prev, T value);
    }

    public Fold(final Signal<Collection<T>> source, final Folder<T, U> folder, final U startValue) {
        super(startValue);
        source.onChange(values -> {
            U acc = startValue;
            for(T t : values) {
                acc = folder.fold(acc, t);
            }
            update(acc);
        });
    }
}
