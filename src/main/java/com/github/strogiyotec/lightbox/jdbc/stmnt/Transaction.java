package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import com.github.strogiyotec.lightbox.jdbc.session.TransactedSession;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * DML query with Transaction
 *
 * @param <T> type
 */
public final class Transaction<T> implements Statement<T> {

    private final Session session;

    private final Callable<T> supplier;

    /**
     * List of supported exceptions for rollback
     */
    private final List<Class<? extends Throwable>> exceptions;

    public Transaction(final TransactedSession session,
                       final Callable<T> supplier,
                       final List<Class<? extends Throwable>> exceptions) {
        this.session = session;
        this.supplier = supplier;
        this.exceptions = exceptions;
    }

    public Transaction(final TransactedSession session,
                       final Callable<T> supplier) {
        this(
                session,
                supplier,
                Collections.singletonList(Exception.class)
        );
    }

    @Override
    public Result<T> result() throws Exception {
        final Connection connection = this.session.connection();
        try {
            final T result = this.supplier.call();
            connection.commit();
            return () -> result;
        } catch (final Exception exc) {
            if (this.support(exc)) {
                connection.rollback();
            } else {
                connection.commit();
            }
            throw exc;
        }
    }

    private boolean support(final Exception exc) {
        return this.exceptions
                .stream()
                .anyMatch(e -> e.equals(exc.getClass()));
    }
}
