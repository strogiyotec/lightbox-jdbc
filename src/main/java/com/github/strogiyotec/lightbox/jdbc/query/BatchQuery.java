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

    private final List<Parameters> values;

    public BatchQuery(final Text query, final List<Parameters> values) {
        this.query = new ParsedSqlQuery(query, values.get(0));
        this.values = values;
    }

    public BatchQuery(final String query, final Parameters... dataValues) {
        this(
                () -> query,
                Arrays.asList(dataValues)
        );
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement statement = connection.prepareStatement(this.query.asString());
        for (final Parameters vals : this.values) {
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
