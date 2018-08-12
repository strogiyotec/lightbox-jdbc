package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.ResultSetRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public final class Select implements Statement<Rows> {

    /**
     * session
     */
    private final Session session;

    /**
     * query
     */
    private final Query query;

    @Override
    public Result<Rows> result() throws SQLException {
        try (final Connection conn = this.session.connection()) {
            try (final PreparedStatement stmt = this.query.prepared(conn)) {
                stmt.execute();
                try (final ResultSet set = stmt.getResultSet()) {
                    final Rows rows = new ResultSetRows(set);
                    return () -> rows;
                }
            }
        }
    }
}
