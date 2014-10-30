package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * A filter that removes any updates where the signal value is not changed.
 * Created by Rimmer on 24/9/2014.
 */
public class ChangeFilter<T> extends Signal<T> {
    public ChangeFilter(Signal<T> s) {
        super(s.get());
        s.onChange(value -> {
            if(value != get()) {
                if (value != null && value.equals(get())) {
                    // Make sure that we have the latest copy of the value,
                    // even if it contains the same data.
                    updateSilent(value);
                } else {
                    update(value);
                }
            }
        });
    }
}