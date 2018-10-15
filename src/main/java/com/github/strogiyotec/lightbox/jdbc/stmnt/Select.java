package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.RowsOfResultSet;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Provide select statement
 */
@AllArgsConstructor
public final class Select implements Statement<Rows> {

    /**
     * session
     */
    private final Session session;

    /**
     * Query
     */
    private final Query query;

    @Override
    public Result<Rows> result() throws Exception {
        try (final Connection conn = this.session.connection()) {
            try (final PreparedStatement stmt = this.query.prepared(conn)) {
                final boolean execute = stmt.execute();
                try (final ResultSet set = stmt.getResultSet()) {
                    final Rows rows = new RowsOfResultSet(execute ? set : EmptyResultSet.EMPTY_RS);
                    return () -> rows;
                }
            }
        }
    }
}
