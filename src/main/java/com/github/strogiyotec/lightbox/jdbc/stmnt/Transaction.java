package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import com.github.strogiyotec.lightbox.jdbc.session.TransactionSession;
import org.jakarta.CheckedSupplier;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public final class Transaction<T> implements Statement<T> {

    private final Session session;

    private final CheckedSupplier<T> supplier;


    /**
     * List of supported exceptions for rollback
     */
    private final List<Class<? extends Throwable>> exceptions;

    public Transaction(final TransactionSession session,
                       final CheckedSupplier<T> supplier,
                       final List<Class<? extends Throwable>> exceptions) {
        this.session = session;
        this.supplier = supplier;
        this.exceptions = exceptions;
    }

    public Transaction(final TransactionSession session,
                       final CheckedSupplier<T> supplier) {
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
            final T result = this.supplier.get();
            connection.commit();
            return () -> result;
        } catch (final Exception exc) {
            if (this.support(exc)) {
                connection.rollback();
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
