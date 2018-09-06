package com.github.strogiyotec.lightbox.jdbc.log;

import org.slf4j.Logger;

import java.sql.PreparedStatement;

public final class LoggedPrepareStatement extends PreparedStatementOf {

    private final Logger log;

    private final int id;

    private final JdbcLog jdbcLog;

    protected LoggedPrepareStatement(final PreparedStatement origin, final int id, final Logger log, final JdbcLog jdbcLog) {
        super(origin);
        this.log = log;
        this.id = id;
        this.jdbcLog = jdbcLog;
    }


}
