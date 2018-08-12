package com.github.strogiyotec.lightbox.jdbc;

import org.jakarta.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Query extends Text {

    /**
     * @param connection A connection to data source
     * @return A {@link PreparedStatement}
     * @throws SQLException if failed
     */
    PreparedStatement prepared(Connection connection) throws SQLException;
}
