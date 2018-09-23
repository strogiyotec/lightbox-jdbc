package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.RowsOfResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class return rows after DML queries as ${{@link Rows}}
 */
public final class GeneratedKeys implements Statement<Rows> {

    /**
     * Session
     */
    private final Session session;

    /**
     * Query
     */
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
                    final Rows maps = new RowsOfResultSet(resultSet);
                    return () -> maps;
                }
            }
        }
    }
}
