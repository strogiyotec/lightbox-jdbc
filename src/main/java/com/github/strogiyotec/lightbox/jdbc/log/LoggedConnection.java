package com.github.strogiyotec.lightbox.jdbc.log;

import com.github.strogiyotec.lightbox.jdbc.session.ConnectionOf;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;


public final class LoggedConnection extends ConnectionOf {

    private final Logger log;

    private final JdbcLog jdbcLog;

    private static AtomicInteger connectionId;

    private final int id;

    public LoggedConnection(final Connection origin, final Logger log, final JdbcLog jdbcLog) {
        super(origin);
        this.log = log;
        this.jdbcLog = jdbcLog;
        this.id = connectionId.getAndIncrement();
    }

    @Override
    public void commit() throws SQLException {
        if (this.jdbcLog.transactions()) {
            this.log.debug("commit is invoked on connection {}", this.id);
        }
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        if (this.jdbcLog.transactions()) {
            this.log.debug("rollback is invoked on connection {}", this.id);
        }
        super.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.origin.close();
        if (this.jdbcLog.connection()) {
            log.debug("Connection {} is closed", this.id);
        }
    }

    @Override
    public PreparedStatement prepareStatement(final String sql) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql);
        return this.loggedPrepareStatement(statement);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql, resultSetType, resultSetConcurrency);
        return this.loggedPrepareStatement(statement);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        return this.loggedPrepareStatement(statement);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql, autoGeneratedKeys);
        return this.loggedPrepareStatement(statement);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql, columnIndexes);
        return this.loggedPrepareStatement(statement);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final String[] columnNames) throws SQLException {
        final PreparedStatement statement = this.origin.prepareStatement(sql, columnNames);
        return this.loggedPrepareStatement(statement);
    }

    private PreparedStatement loggedPrepareStatement(final PreparedStatement originPS) {
        if (this.jdbcLog.resultSet() || this.jdbcLog.sqlAndTime() || this.jdbcLog.sqlOnly()) {
            return new LoggedPrepareStatement(originPS, this.id, this.log, this.jdbcLog);
        } else {
            return originPS;
        }
    }
}
