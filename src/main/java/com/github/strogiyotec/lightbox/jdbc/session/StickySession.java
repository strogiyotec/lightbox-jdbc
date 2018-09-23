package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import org.jakarta.CheckedSupplier;

import java.sql.Connection;

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

    public StickySession(final CheckedSupplier<Connection> connection) throws Exception {
        this(
                connection.get()
        );
    }

    @Override
    public Connection connection() throws Exception {
        return this.connection;
    }
}
