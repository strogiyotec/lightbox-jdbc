package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Insert
 */
public final class Insert implements Statement<Boolean> {

    /**
     * Session
     */
    private final Session session;

    /**
     * Query
     */
    private final Query query;

    public Insert(final Session session, final Query query) {
        this.session = session;
        this.query = query;
    }

    @Override
    public Result<Boolean> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement stmt = this.query.prepared(connection)) {
                final boolean hasResult = stmt.execute();
                return () -> hasResult;
            }
        }
    }
}
