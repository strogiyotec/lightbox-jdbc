package com.github.strogiyotec.lightbox.jdbc;

import java.sql.Connection;

/**
 * Session.
 * Create a {@link java.sql.Connection}
 */
public interface Session {

    /**
     * Create a {@link Connection}
     *
     * @return {@link Connection}
     * @throws Exception if fails
     */
    Connection connection() throws Exception;
}
