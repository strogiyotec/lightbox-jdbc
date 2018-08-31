package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.ResultSetRows;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class return rows after DML queries as ${{@link Rows}}
 */
@ToString(of = "query")
public final class GeneratedKeys implements Statement<Rows> {

    private final Session session;

    private final Query query;

    public GeneratedKeys(final Session session, final KeyedQuery query) {
        this.session = session;
        this.query = query;
    }

    @Override
    public Result<Rows> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement statement = this.query.prepared(connection)) {
                statement.execute();
                try (final ResultSet resultSet = statement.getGeneratedKeys()) {
                    final Rows maps = new ResultSetRows(resultSet);
                    return () -> maps;
                }
            }
        }
    }
}
