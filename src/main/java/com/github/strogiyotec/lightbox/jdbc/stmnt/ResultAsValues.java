package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public final class ResultAsValues<T> implements Statement<List<T>> {

    private final Statement<Rows> origin;

    private final Class<T> type;

    @Override
    public Result<List<T>> result() throws Exception {
        final Rows rows = this.origin.result().get();
        return () -> this.valuesFromIterator(rows.iterator());
    }

    private List<T> valuesFromIterator(final Iterator<Map<String, Object>> iterator) {
        final List<T> values = new LinkedList<>();
        while (iterator.hasNext()) {
            final Map<String, Object> next = iterator.next();
            final List<String> keys = new ArrayList<>(next.keySet());
            if (!keys.isEmpty()) {
                values.add(this.type.cast(next.get(keys.get(0))));
            }
        }
        return values;
    }
}
