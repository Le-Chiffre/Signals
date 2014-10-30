package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Returns the highest value of the provided signals.
 * Created by Rimmer on 24/9/2014.
 */
public class Max<T extends Comparable> extends Signal<T> {
    private T max;

    public Max(Iterable<Signal<T>> signals) {
        super(findMax(signals));
        max = get();

        for(Signal<T> s : signals) {
            s.onChange(value -> {
                if(max == null || value.compareTo(max) > 0) {
                    max = value;
                    update(max);
                }
            });
        }
    }

    private static <T extends Comparable> T findMax(Iterable<Signal<T>> signals) {
        T res = signals.iterator().next().get();
        for(Signal<T> s : signals) {
            T v = s.get();
            if(v.compareTo(res) > 0) {
                res = v;
            }
        }

        assert(res != null);
        return res;
    }
}