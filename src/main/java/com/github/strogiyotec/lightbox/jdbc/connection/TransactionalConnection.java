package com.github.strogiyotec.lightbox.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.function.Supplier;

/**
 * Represent a connection which is closed only when
 * {@link #origin}  commit or rollback method called
 * Package friendly to avoid usage without transaction
 */
@Slf4j
public final class TransactionalConnection extends ConnectionOf {

    public TransactionalConnection(final Connection origin) {
        super(origin);
    }

    public TransactionalConnection(final Supplier<Connection> origin) {
        super(origin);
    }


    @Override
    public void commit() throws SQLException {
        try {
            this.origin.commit();
        } finally {
            if (!this.origin.isClosed()) {
                this.origin.close();
            }
        }
    }

    @Override
    public void rollback() throws SQLException {
        try {
            this.origin.rollback();
        } finally {
            if (!this.origin.isClosed()) {
                this.origin.close();
            }
        }
    }

    @Override
    public void close() throws SQLException {
        //do nothing
    }

    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        try {
            this.origin.rollback(savepoint);
        } finally {
            if (!this.origin.isClosed()) {
                this.origin.close();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (!this.origin.isClosed()) {
                log.warn("You don't close connection {} use commit or rollback to close it", this.origin);
            }
        } finally {
            super.finalize();
        }

    }
}
