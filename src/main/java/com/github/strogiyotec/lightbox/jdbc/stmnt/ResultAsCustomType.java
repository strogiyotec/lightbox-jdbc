package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * This class map single result using ${{@link mapper}}
 * @param <T> type
 */
@AllArgsConstructor
public final class ResultAsCustomType<T> implements Statement<T>{

    private final Statement<Rows> origin;

    private final Function<Object,T> mapper;

    @Override
    public Result<T> result() throws Exception {
        final ResultAsValue<Object> value = new ResultAsValue<>(
                this.origin,
                Object.class
        );
        final Object result = value.result().get();
        return ()->this.mapper.apply(result);
    }
}
