package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.connection.TransactionalConnection;

import java.sql.Connection;
import java.util.concurrent.Callable;

/**
 * Transacted session
 */
public final class TransactedSession implements Session {

    /**
     * Origin
     */
    private final Session origin;

    public TransactedSession(final Session origin) throws Exception {
        this.origin = new StickySession((Callable<Connection>) () -> {
            final Connection connection = origin.connection();
            connection.setAutoCommit(false);
            return new TransactionalConnection(connection);
        });
    }

    @Override
    public Connection connection() throws Exception {
        return this.origin.connection();
    }
}
