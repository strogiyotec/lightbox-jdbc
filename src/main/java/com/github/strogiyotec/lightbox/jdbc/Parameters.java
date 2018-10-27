package com.github.strogiyotec.lightbox.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A set of Data values
 */
public interface Parameters extends Iterable<Parameter<?>> {
    /**
     * Set the {@link PreparedStatement} with all data values
     *
     * @param statement The {@link PreparedStatement}
     * @return new {@link PreparedStatement}
     * @throws SQLException if failed
     */
    PreparedStatement prepare(PreparedStatement statement) throws SQLException;

    /**
     * @return True if contains param with name in position
     */
    boolean contains(String name, int pos);
}
