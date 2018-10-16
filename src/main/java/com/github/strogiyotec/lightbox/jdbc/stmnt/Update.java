package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * This class represent amount of rows in DB which were affected
 * by this statement
 */
@AllArgsConstructor
public final class Update implements Statement<Integer> {

    /**
     * Session
     */
    private final Session session;

    /**
     * Query
     */
    private final Query query;

    @Override
    public Result<Integer> result() throws Exception {
        try (final Connection connection = session.connection()) {
            try (final PreparedStatement statement = this.query.prepared(connection)) {
                final int rows = statement.executeUpdate();
                return () -> rows;
            }
        }
    }
}
