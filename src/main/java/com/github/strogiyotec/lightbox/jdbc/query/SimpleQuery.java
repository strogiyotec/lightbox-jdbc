package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.value.data.QueryParams;
import lombok.AllArgsConstructor;
import org.jakarta.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Simple sql Query
 */
@AllArgsConstructor
public final class SimpleQuery implements Query {

    /**
     * Sql query
     */
    private final Text sql;

    /**
     * Sql params
     */
    private final Parameters values;

    public SimpleQuery(final String sql, final Parameter<?>... values) {
        this(
                () -> sql,
                values
        );
    }

    public SimpleQuery(final Text sql, final Parameter<?>... vals) {
        this.sql = new ParsedSqlQuery(sql, vals);
        this.values = new QueryParams(vals);
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement stmt = connection.prepareStatement(this.sql.asString());
        this.values.prepare(stmt);
        return stmt;
    }

    @Override
    public String asString() throws Exception {
        return this.sql.asString();
    }
}
