package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;

@AllArgsConstructor
public final class Insert implements Statement<Integer> {

    private final Query query;

    private final Session session;

    @Override
    public Result<Integer> result() throws Exception {
        try (final Connection connection = this.session.connection()) {
            try (final PreparedStatement statement = this.query.prepared(connection)) {
                final int i = statement.executeUpdate();
                return ()->i;
            }
        }
    }
}
