package com.github.strogiyotec.lightbox.jdbc.log;

import java.sql.PreparedStatement;

public abstract class PreparedStatementOf implements PreparedStatement {

    protected final PreparedStatement origin;

    protected PreparedStatementOf(final PreparedStatement origin) {
        this.origin = origin;
    }
}
