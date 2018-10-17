package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Decimal val.
 *
 * @since 0.1
 */
public final class DecimalValue implements Parameter<BigDecimal> {
    /**
     * Name.
     */
    private final String nam;

    /**
     * Value.
     */
    private final BigDecimal val;

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public DecimalValue(final String name, final String value) {
        this(name, new BigDecimal(value));
    }

    /**
     * Ctor.
     *
     * @param name  The name
     * @param value The value
     */
    public DecimalValue(final String name, final BigDecimal value) {
        this.nam = name;
        this.val = value;
    }

    @Override
    public String name() {
        return this.nam;
    }

    @Override
    public BigDecimal get() {
        return this.val;
    }

    @Override
    public void prepare(
            final PreparedStatement stmt,
            final int index
    ) throws SQLException {
        stmt.setBigDecimal(index, this.val);
    }

    @Override
    public String asString() throws IOException {
        return this.val.toString();
    }
}
