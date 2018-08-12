package com.github.strogiyotec.lightbox.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Session.
 * Create a {@link java.sql.Connection}
 */
public interface Session {

    /**
     * Create a {@link Connection}
     *
     * @return {@link Connection}
     * @throws SQLException if fails
     */
    Connection connection() throws SQLException;
}
