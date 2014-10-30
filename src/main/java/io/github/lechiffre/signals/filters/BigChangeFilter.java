package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Filters away small changes in the source signal.
 * Only updates itself when the total change is larger than the provided value.
 */
public class BigChangeFilter<T extends Number> extends Signal<T> {
    final double maxDev;

    public BigChangeFilter(Signal<T> s, Number changeBiggerThan) {
        super(s.get());
        maxDev = changeBiggerThan.doubleValue();
        s.onChange(value -> {
            double v = value.doubleValue();
            double c = get().doubleValue();
            if(bigEnoughChange(v,c)) update(value);
        });
    }

    private boolean bigEnoughChange(double v, double c) {
        return changeDownBigEnough(v,c)
            || changeUpBigEnough(v, c);
    }

    private boolean changeDownBigEnough(double newValue, double oldValue){
        return newValue < oldValue - maxDev;
    }

    private boolean changeUpBigEnough(double newValue, double oldValue){
        return newValue > oldValue + maxDev;
    }
}
