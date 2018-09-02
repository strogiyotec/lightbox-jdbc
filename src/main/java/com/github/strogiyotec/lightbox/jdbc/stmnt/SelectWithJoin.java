package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.JoinRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public final class SelectWithJoin implements Statement<Rows> {

    private final Session session;

    private final Query query;

    private final Tables tables;

    @Override
    public Result<Rows> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement st = this.query.prepared(connection)) {
                st.execute();
                try (final ResultSet rs = st.getResultSet()) {
                    final Rows rows = new JoinRows(rs, this.tables);
                    return () -> rows;
                }
            }
        }
    }


}
