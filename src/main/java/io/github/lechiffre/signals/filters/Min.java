package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Returns the lowest value of the provided signals.
 * Created by Rimmer on 24/9/2014.
 */
public class Min<T extends Comparable> extends Signal<T> {
    private T min;

    public Min(Iterable<Signal<T>> signals) {
        super(findMin(signals));
        min = get();

        for(Signal<T> s : signals) {
            s.onChange(value -> {
                if(min == null || value.compareTo(min) < 0) {
                    min = value;
                    update(min);
                }
            });
        }
    }

    private static <T extends Comparable> T findMin(Iterable<Signal<T>> signals) {
        T res = signals.iterator().next().get();
        for(Signal<T> s : signals) {
            T v = s.get();
            if(v.compareTo(res) < 0) {
                res = v;
            }
        }

        assert(res != null);
        return res;
    }
}
