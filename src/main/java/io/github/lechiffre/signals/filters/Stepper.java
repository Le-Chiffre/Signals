package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Turns the provided continuous input signal into discrete steps of size stepSize.
 */
public class Stepper<T extends Number> extends Signal<T> {
    final double stepSizeAsDouble;

    public Stepper(Signal<T> s, Number stepSize) {
        super(s.get());
        stepSizeAsDouble = stepSize.doubleValue();
        s.onChange(value -> {
            double valueAsDouble = value.doubleValue();
            double getAsDouble = get().doubleValue();
            if (isDifferentStep(valueAsDouble,getAsDouble)) {
                update(value);
            }
        });
    }


    private boolean isDifferentStep(double newValue, double oldValue){
        return Math.abs(stepValue(newValue)-stepValue(oldValue))>=1;
    }

    private int stepValue(double value){
        return (int)(value/stepSizeAsDouble);
    }
}