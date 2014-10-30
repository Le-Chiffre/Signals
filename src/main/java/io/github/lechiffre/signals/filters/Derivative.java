package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Calculates the derivative of a floating point signal.
 * Created by rimmer on 06/10/14.
 */
public class Derivative extends Signal<Float> {
    private long lastTime;
    private float prevSource;

    public Derivative(Signal<Float> source) {
        super(0f);
        lastTime = System.currentTimeMillis();
        prevSource = source.get();

        source.onChange(value -> {
            long currTime = System.currentTimeMillis();
            long dt = currTime - lastTime;
            lastTime = currTime;

            update((value - prevSource) / (float)dt);
            prevSource = value;
        });
    }
}
