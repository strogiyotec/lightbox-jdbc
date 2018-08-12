package com.github.strogiyotec.lightbox.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A set of Data values
 */
public interface DataValues extends Iterable<DataValue<?>> {

    /**
     * Add a value into {@link DataValues}
     *
     * @param value to be added
     * @return The new {@link DataValues}
     */
    DataValues with(DataValue<?> value);

    /**
     * Set the {@link PreparedStatement} with all data values
     *
     * @param statement The {@link PreparedStatement}
     * @return new {@link PreparedStatement}
     * @throws SQLException if failed
     */
    PreparedStatement prepare(PreparedStatement statement) throws SQLException;
}
