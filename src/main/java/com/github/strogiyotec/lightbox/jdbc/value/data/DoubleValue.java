package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Double val.
 *
 * @since 0.1
 */
public final class DoubleValue implements Parameter<Double> {
    /**
     * Value.
     */
    private final String nam;

    /**
     * Value.
     */
    private final Double val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The val
     */
    public DoubleValue(final String name, final Double value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public Double get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setDouble(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
