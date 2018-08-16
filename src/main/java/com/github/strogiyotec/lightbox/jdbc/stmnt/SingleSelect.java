package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public final class SingleSelect<T> implements Statement<T> {

    /**
     * origin
     */
    private final Statement<Rows> origin;

    /**
     * type
     */
    private final Class<T> type;

    @Override
    public Result<T> result() throws SQLException {
        try {
            final Map<String, Object> value = this.origin.result().get().iterator().next();
            return () ->
                    this.type.cast(value.get(new ArrayList<>(value.keySet()).get(0)));

        } catch (final Exception e) {
            throw new SQLException(e);
        }
    }
}
