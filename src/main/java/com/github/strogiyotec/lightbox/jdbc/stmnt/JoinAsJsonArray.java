package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import lombok.AllArgsConstructor;

import javax.json.JsonArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public final class JoinAsJsonArray implements Statement<JsonArray> {

    private final Session session;

    private final Query query;

    private final Tables<JsonArray> tables;

    @Override
    public Result<JsonArray> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement st = this.query.prepared(connection)) {
                st.execute();
                try (final ResultSet rs = st.getResultSet()) {
                    final JsonValuesOf jsonValuesOf = new JsonValuesOf(rs, tables);
                    return () -> jsonValuesOf;
                }
            }
        }
    }
}
