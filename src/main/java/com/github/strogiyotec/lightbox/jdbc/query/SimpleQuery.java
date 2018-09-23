package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.DataValue;
import com.github.strogiyotec.lightbox.jdbc.DataValues;
import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.value.data.CombinedDataValues;
import lombok.AllArgsConstructor;
import org.jakarta.Text;

import java.io.IOException;
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
    private final DataValues values;

    public SimpleQuery(final String sql, final DataValue<?>... values) {
        this(
                () -> sql,
                values
        );
    }

    public SimpleQuery(final Text sql, final DataValue<?>... vals) {
        this.sql = new ParsedSqlQuery(sql, vals);
        this.values = new CombinedDataValues(vals);
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement stmt = connection.prepareStatement(this.sql.asString());
        this.values.prepare(stmt);
        return stmt;
    }

    @Override
    public String asString() throws IOException {
        return this.sql.asString();
    }
}
