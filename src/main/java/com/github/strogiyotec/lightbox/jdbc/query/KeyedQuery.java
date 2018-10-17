package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.value.data.CombinedParameters;
import org.jakarta.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Query with GENERATED_KEYS
 */
public final class KeyedQuery implements Query {

    /**
     * Sql query
     */
    private final Text sql;

    /**
     * Sql params
     */
    private final Parameters values;


    public KeyedQuery(final String sql, final Parameter<?>... values) {
        this(
                () -> sql,
                values
        );
    }

    public KeyedQuery(final Text sql, final Parameter<?>... vals) {
        this.sql = new ParsedSqlQuery(sql, vals);
        this.values = new CombinedParameters(vals);
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement stmt = connection.prepareStatement(
                this.sql.asString(), Statement.RETURN_GENERATED_KEYS
        );
        this.values.prepare(stmt);
        return stmt;
    }

    @Override
    public String asString() throws Exception {
        return this.sql.asString();
    }
}
