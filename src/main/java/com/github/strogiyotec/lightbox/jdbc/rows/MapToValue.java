package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import lombok.AllArgsConstructor;
import org.jakarta.CheckedSupplier;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public final class MapToValue<T> implements Function<Rows,T>{

    /**
     * Mapper from Rows to type
     */
    private final Function<Rows,T> mapper;

    @Override
    public T apply(final Rows rows) {
        return null;
    }
}
