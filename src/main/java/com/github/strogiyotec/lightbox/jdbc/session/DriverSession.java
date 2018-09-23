package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Driver session
 */
@AllArgsConstructor
@ToString(of = "url")
@EqualsAndHashCode(of = "url")
public final class DriverSession implements Session {

    /**
     * Jdbc url
     */
    private final String url;

    /**
     * User login name
     */
    private final transient String userName;

    /**
     * user password
     */
    private final transient String password;

    @Override
    public Connection connection() throws Exception {
        return DriverManager.getConnection(
                this.url,
                this.userName,
                this.password
        );
    }
}
