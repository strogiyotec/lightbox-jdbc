package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.DataValue;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Long val.
 *
 * @since 0.1
 */
public final class LongValue implements DataValue<Long> {
    /**
     * Name.
     */
    private final String nam;

    /**
     * Value.
     */
    private final Long val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public LongValue(final String name, final Long value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public Long get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setLong(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
