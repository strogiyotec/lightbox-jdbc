package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import org.jakarta.CheckedSupplier;

import java.sql.Connection;

public final class TransactedSession implements Session {

    private final Session origin;

    public TransactedSession(final Session origin) throws Exception{
        this.origin = new StickySession((CheckedSupplier<Connection>) () -> {
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
