package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Parameters;
import com.github.strogiyotec.lightbox.jdbc.Query;
import org.jakarta.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

public final class BatchQuery implements Query {

    private final Text query;

    private final List<Parameters> params;

    public BatchQuery(final Text query, final Parameters... values) {
        this.query = new ParsedSqlQuery(query, values[0]);
        this.params = Arrays.asList(values);
    }

    public BatchQuery(final String query, final Parameters... dataValues) {
        this.query = new ParsedSqlQuery(query, dataValues[0]);
        this.params = Arrays.asList(dataValues);
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement statement = connection.prepareStatement(this.query.asString());
        for (final Parameters vals : this.params) {
            vals.prepare(statement);
            statement.addBatch();
        }
        return statement;
    }

    @Override
    public String asString() throws Exception {
        return this.query.asString();
    }
}
