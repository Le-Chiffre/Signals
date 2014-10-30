package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

/**
 * Subtracts the two provided signals.
 */
public class Sub<T extends Number> extends Signal<T> {
    public Sub(final Signal<T> a, final Signal<T> b) {
        super(sub(a.get(), b.get()));
        a.onChange(value -> update(sub(value, b.get())));
        b.onChange(value -> update(sub(a.get(), value)));
    }

    private static <T> T sub(T a, T b) {
        // This is really ugly, but Java has no way of 
        // performing arithmetic without knowing the actual type.
        if(a instanceof Integer) {
            return (T)new Integer((Integer)a - (Integer)b);
        } else if(a instanceof Long) {
            return (T)new Long((Long)a - (Long)b);
        } else if(a instanceof Float) {
            return (T)new Float((Float)a - (Float)b);
        } else if(a instanceof Double) {
            return (T)new Double((Double)a - (Double)b);
        } else {
            assert("Unsupported type" == null);
            return a;
        }
    }
}
