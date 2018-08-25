package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import lombok.AllArgsConstructor;

import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public final class ConfigurableSession implements Session {

    /**
     * Origin session
     */
    private final Session origin;

    /**
     * Configuration for connection
     */
    private final JsonObject configuration;

    @Override
    public Connection connection() throws SQLException {
        final Connection connection = this.origin.connection();
        connection.setReadOnly(this.configuration.getBoolean("readOnly", false));
        connection.setAutoCommit(this.configuration.getBoolean("autoCommit", false));
        connection.setTransactionIsolation(this.configuration.getInt("isolation", connection.getTransactionIsolation()));
        return connection;
    }
}
