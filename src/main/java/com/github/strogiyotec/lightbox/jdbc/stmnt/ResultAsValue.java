package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public final class ResultAsValue<T> implements Statement<T> {

    /**
     * origin
     */
    private final Statement<Rows> origin;

    /**
     * type
     */
    private final Class<T> type;

    @Override
    public Result<T> result() throws Exception {
        final Rows result = this.origin.result().get();
        checkRowsOnEmpty(result);
        final Map<String, Object> value = result.iterator().next();
        return () ->
                this.type.cast(
                        value.get(new ArrayList<>(value.keySet()).get(0))
                );
    }

    private static void checkRowsOnEmpty(final Rows rows) {
        if (Iterables.isEmpty(rows)) {
            throw new IllegalStateException("Sql query return empty result set");
        }
    }
}
