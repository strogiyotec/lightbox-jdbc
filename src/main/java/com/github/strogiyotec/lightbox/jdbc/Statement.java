package com.github.strogiyotec.lightbox.jdbc;

import java.sql.SQLException;

/**
 * Sql statement
 *
 * @param <T> Type of the result
 */
public interface Statement<T> {

    /**
     * @return A Result of a type
     * @throws SQLException If fails
     */
    Result<T> result() throws SQLException;
}
