package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.JoinedRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Statement with join
 */
@AllArgsConstructor
public final class Join implements Statement<Rows> {

    /**
     * Session
     */
    private final Session session;

    /**
     * Query
     */
    private final Query query;

    /**
     * Tables to be joined
     */
    private final Tables tables;

    @Override
    public Result<Rows> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement st = this.query.prepared(connection)) {
                st.execute();
                try (final ResultSet rs = st.getResultSet()) {
                    final Rows rows = new JoinedRows(rs, this.tables);
                    return () -> rows;
                }
            }
        }
    }


}
