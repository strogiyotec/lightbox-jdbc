package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Integer val.
 *
 * @since 0.1
 */
public final class IntValue implements Parameter<Integer> {
    /**
     * Value.
     */
    private final String nam;

    /**
     * Value.
     */
    private final Integer val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public IntValue(final String name, final Integer value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public Integer get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setInt(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
