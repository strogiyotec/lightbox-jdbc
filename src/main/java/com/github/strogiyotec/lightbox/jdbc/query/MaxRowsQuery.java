package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Query;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.function.Supplier;

/**
 * Query with limit size
 */
@AllArgsConstructor
public final class MaxRowsQuery implements Query {

    /**
     * Oriign query
     */
    private final Query origin;

    /**
     * Limit size
     */
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
    public String asString() throws Exception {
        return this.origin.asString();
    }
}
