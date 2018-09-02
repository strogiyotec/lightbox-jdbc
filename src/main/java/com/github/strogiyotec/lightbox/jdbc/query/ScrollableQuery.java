/*
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

@AllArgsConstructor
public final class ScrollableQuery implements Query {

    */
/**
     * Sql query
     *//*

    private final Text sql;

    */
/**
     * Sql params
     *//*

    private final DataValues values;

    public ScrollableQuery(final String sql, final DataValue<?>... values) {
        this(
                () -> sql,
                values
        );
    }

    public ScrollableQuery(final Text sql, final DataValue<?>... vals) {
        this.sql = new ParsedSqlQuery(sql, vals);
        this.values = new CombinedDataValues(vals);
    }

    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {

    }

    @Override
    public String asString() throws IOException {
        return this.sql.asString();
    }
}
*/