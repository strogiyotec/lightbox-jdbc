package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public final class TransactionSession implements Session {

    private final Session origin;

    @Override
    public Connection connection() throws SQLException {
        final Connection connection = this.origin.connection();
        connection.setAutoCommit(false);
        return connection;
    }
}
