package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Date val.
 *
 * @since 0.1
 */
public final class DateValue implements Parameter<LocalDate> {
    /**
     * Column.
     */
    private final String nam;

    /**
     * Column.
     */
    private final LocalDate val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public DateValue(final String name, final String value) {
        this(name, LocalDate.parse(value));
    }

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public DateValue(final String name, final LocalDate value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public LocalDate get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setDate(index, java.sql.Date.valueOf(this.val));
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
