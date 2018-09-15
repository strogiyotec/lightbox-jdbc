package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.DataValues;
import com.github.strogiyotec.lightbox.jdbc.Query;
import org.jakarta.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

public final class BatchQuery implements Query {

    private final Text query;

    private final List<DataValues> values;

    public BatchQuery(final Text query, final List<DataValues> values) {
        this.query = new ParsedSqlQuery(query, values.get(0));
        this.values = values;
    }

    public BatchQuery(final String query, final DataValues... dataValues) {
        this(
                () -> query,
                Arrays.asList(dataValues)
        );
    }


    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement statement = connection.prepareStatement(this.query.asString());
        for (final DataValues vals : this.values) {
            vals.prepare(statement);
            statement.addBatch();
        }
        return statement;
    }

    @Override
    public String asString() throws IOException {
        return this.query.asString();
    }
}
