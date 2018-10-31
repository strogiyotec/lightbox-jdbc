package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DateTime val.
 *
 * @since 0.1
 */
public final class DateTimeValue implements Parameter<LocalDateTime> {
    /**
     * Column.
     */
    private final String nam;

    /**
     * Column.
     */
    private final LocalDateTime val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public DateTimeValue(final String name, final LocalDateTime value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public LocalDateTime get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setTimestamp(index, Timestamp.valueOf(this.val));
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
