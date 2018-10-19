package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;

import java.sql.Connection;
import java.util.concurrent.Callable;

/**
 * Session of single connection
 */
public final class StickySession implements Session {

    /**
     * Connection
     */
    private final Connection connection;

    public StickySession(final Connection connection) throws Exception {
        this.connection = connection;
    }

    public StickySession(final Session session) throws Exception {
        this(
                session.connection()
        );
    }

    public StickySession(final Callable<Connection> connection) throws Exception {
        this(
                connection.call()
        );
    }

    @Override
    public Connection connection() throws Exception {
        return this.connection;
    }
}
