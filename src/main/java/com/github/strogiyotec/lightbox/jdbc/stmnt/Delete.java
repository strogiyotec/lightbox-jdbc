package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public final class Delete implements Statement<Integer> {

    private final Session session;

    private final Query query;

    public Delete(final Session session, final Query query) {
        this.session = session;
        this.query = query;
    }

    @Override
    public Result<Integer> result() throws Exception {
        try (final Connection connection = session.connection()) {
            try (final PreparedStatement statement = this.query.prepared(connection)) {
                final int rows = statement.executeUpdate();
                return () -> rows;
            }
        }
    }
}
