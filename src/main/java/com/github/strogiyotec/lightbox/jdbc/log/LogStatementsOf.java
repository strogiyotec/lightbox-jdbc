package com.github.strogiyotec.lightbox.jdbc.log;

import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * Log given sql params
 */
@ToString
public final class LogStatementsOf implements LogStatements {

    private final boolean sqlAndTime;

    private final boolean sqlOnly;

    private final boolean audit;

    private final boolean resultSet;

    private final boolean connection;

    private final boolean transaction;

    public LogStatementsOf(final List<LogStatements> origin) {
        boolean sqlAndTime = false, sqlOnly = false, audit = false, resultSet = false, connection = false, transaction = false;
        for (final LogStatements jdbcLog : origin) {
            if (jdbcLog.audit()) {
                audit = true;
                continue;
            }
            if (jdbcLog.connection()) {
                connection = true;
                continue;
            }
            if (jdbcLog.resultSet()) {
                resultSet = true;
                continue;
            }
            if (jdbcLog.sqlAndTime()) {
                sqlAndTime = true;
                continue;
            }
            if (jdbcLog.sqlOnly()) {
                sqlOnly = true;
                continue;
            }
            if (jdbcLog.transactions()) {
                transaction = true;
            }
        }
        this.audit = audit;
        this.connection = connection;
        this.resultSet = resultSet;
        this.sqlAndTime = sqlAndTime;
        this.sqlOnly = sqlOnly;
        this.transaction = transaction;
    }

    public LogStatementsOf(final LogStatements... origin) {
        this(
                Arrays.asList(origin)
        );
    }

    @Override
    public boolean sqlAndTime() {
        return this.sqlAndTime;
    }

    @Override
    public boolean sqlOnly() {
        return this.sqlOnly;
    }

    @Override
    public boolean audit() {
        return this.audit;
    }

    @Override
    public boolean resultSet() {
        return this.resultSet;
    }

    @Override
    public boolean connection() {
        return this.connection;
    }

    @Override
    public boolean transactions() {
        return this.transaction;
    }
}