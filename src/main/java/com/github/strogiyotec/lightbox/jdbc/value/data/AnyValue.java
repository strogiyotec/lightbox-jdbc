package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Any val.
 */
public final class AnyValue implements Parameter<Object> {
    /**
     * Column.
     */
    private final String nam;

    /**
     * Column.
     */
    private final Object val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public AnyValue(final String name, final Object value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public Object get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setObject(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
