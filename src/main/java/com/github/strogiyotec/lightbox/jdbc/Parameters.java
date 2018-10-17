package com.github.strogiyotec.lightbox.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A set of Data values
 */
public interface Parameters extends Iterable<Parameter<?>> {

    /**
     * Add a value into {@link Parameters}
     *
     * @param value to be added
     * @return The new {@link Parameters}
     */
    Parameters with(Parameter<?> value);

    /**
     * Set the {@link PreparedStatement} with all data values
     *
     * @param statement The {@link PreparedStatement}
     * @return new {@link PreparedStatement}
     * @throws SQLException if failed
     */
    PreparedStatement prepare(PreparedStatement statement) throws SQLException;

    /**
     * @return amount of parameters
     */
    int amount();
}
