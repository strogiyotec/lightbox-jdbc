package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Boolean val.
 */
public final class BoolValue implements Parameter<Boolean> {
    /**
     * Value.
     */
    private final String nam;

    /**
     * Value.
     */
    private final Boolean val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public BoolValue(final String name, final Boolean value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public Boolean get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setBoolean(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
