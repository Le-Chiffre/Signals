package io.github.lechiffre.signals;

import io.github.lechiffre.signals.filters.*;

/**
 * Helper class that allows creating signals like functions.
 * Needs to be used through static import:
 * import static nu.babbla.common.signals.util.SignalBuilder.*;
 *
 * Filters can then be created like this:
 * changeFilter(greater(aga.speed, constant(50f)));
 */
public final class SignalBuilder {
    public static <T> void bind(Listener<T> listener, Signal<T> signal) {
        signal.onChange(listener);
    }

    public static <T> Signal<T> constant(T v) {
        return new Constant<>(v);
    }

    public static <T> Signal<T> add(Signal<T> a, Signal<T> b) {
        return new Add(a, b);
    }

    public static <T> Signal<T> sub(Signal<T> a, Signal<T> b) {
        return new Sub(a, b);
    }

    public static <T> Signal<T> changeFilter(Signal<T> a) {
        return new ChangeFilter<>(a);
    }

    public static <T extends Number> Signal<T> bigChangeFilter(Signal<T> a, T delta) {
        return new BigChangeFilter<>(a, delta);
    }

    public static <T extends Comparable<T>> Signal<Boolean> greater(Signal<T> a, Signal<T> b) {
        return new Greater<>(a, b);
    }

    public static <T extends Comparable<T>> Signal<Boolean> equal(Signal<T> a, Signal<T> b) {
        return new Equal<>(a, b);
    }

    public static <T extends Comparable<T>> Signal<Boolean> lower(Signal<T> a, Signal<T> b) {
        return new Lower<>(a, b);
    }

    public static <T extends Comparable<T>> Signal<Comparison> compare(Signal<T> a, Signal<T> b) {
        return new Compare<>(a, b);
    }

    public static <T> Signal<T> predicate(Signal<T> a, Signal<Boolean> pred) {
        return new Predicate<>(a, pred);
    }

    public static Signal<Void> predicate(Signal<Boolean> a) {
        return new Predicate<>(a);
    }
}
