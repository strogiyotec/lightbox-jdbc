package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Execute batch
 */
public final class Batch implements Statement<int[]> {

    /**
     * Session
     */
    private final Session session;

    /**
     * Query
     */
    private final Query query;

    public Batch(final Session session, final Query query) {
        this.session = session;
        this.query = query;
    }

    @Override
    public Result<int[]> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement statement = this.query.prepared(connection)) {
                final int[] rows = statement.executeBatch();
                return () -> rows;
            }
        }
    }
}
