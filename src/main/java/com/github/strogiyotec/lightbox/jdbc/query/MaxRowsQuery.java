package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Query;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.function.Supplier;

@AllArgsConstructor
public final class MaxRowsQuery implements Query {

    private final Query origin;

    private final int size;

    public MaxRowsQuery(final Supplier<Query> querySupplier, final int size) {
        this(querySupplier.get(), size);
    }

    @Override
    public PreparedStatement prepared(final Connection connection) throws Exception {
        final PreparedStatement prepared = this.origin.prepared(connection);
        prepared.setMaxRows(size);
        return prepared;
    }

    @Override
    public String asString() throws IOException {
        return this.origin.asString();
    }

    @Override
    public String toString() {
        try {
            return this.origin.asString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
