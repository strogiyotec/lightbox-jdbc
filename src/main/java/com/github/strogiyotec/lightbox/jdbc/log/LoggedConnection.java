package com.github.strogiyotec.lightbox.jdbc.log;

import com.github.strogiyotec.lightbox.jdbc.session.ConnectionOf;
import org.slf4j.Logger;

import java.sql.Connection;
import java.util.function.Supplier;

public final class LoggedConnection extends ConnectionOf {

    private final Logger log;

    public LoggedConnection(final Connection origin, final Logger logger) {
        super(origin);
        this.log = logger;
    }

    public LoggedConnection(final Supplier<Connection> origin, final Supplier<Logger> logger) {
        super(origin);
        this.log = logger.get();
    }
}
