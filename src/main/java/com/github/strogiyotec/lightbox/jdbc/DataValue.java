package com.github.strogiyotec.lightbox.jdbc;

import org.jakarta.Text;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * A named value
 */
public interface DataValue<T> extends Supplier<T>, Text {

    /**
     * Get the data value name.
     *
     * @return The name
     */
    String name();

    /**
     * @param statement {@link PreparedStatement}
     * @param index     Position in {@link PreparedStatement}
     * @throws SQLException if failed
     */
    void prepare(PreparedStatement statement, int index) throws SQLException;
}
