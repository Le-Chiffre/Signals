package io.github.lechiffre.signals.filters;

import io.github.lechiffre.signals.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Applies the provided mapping function on each element in the source signal.
 */
public class Map<T, U> extends Signal<Collection<U>> {
    private final Mapper<T, U> mapper;

    public interface Mapper<T, U> {
        U map(T val);
    }

    public Map(final Signal<Collection<T>> sources, final Mapper<T, U> mapper) {
        super(map(mapper, sources.get()));
        this.mapper = mapper;

        sources.onChange(values -> update(map(values)));
    }

    private static <T, U> Collection<U> map(Mapper<T, U> mapper, Collection<T> values) {
        ArrayList<U> list = new ArrayList<>();
        for(T c : values) {
            list.add(mapper.map(c));
        }
        return list;
    }

    private Collection<U> map(Collection<T> values) {
        return map(mapper, values);
    }
}
