package com.github.strogiyotec.lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.log.LogStatements;
import com.github.strogiyotec.lightbox.jdbc.connection.LoggedConnection;
import org.slf4j.Logger;

import java.sql.Connection;

public final class LoggedSession implements Session{

    private final Session origin;

    private final Logger logger;

    private final Query query;

    private final LogStatements logStatements;

    public LoggedSession(final Session origin, final Logger logger, final Query query, final LogStatements logStatements) {
        this.origin = origin;
        this.logger = logger;
        this.query = query;
        this.logStatements = logStatements;
    }

    @Override
    public Connection connection() throws Exception {
        return new LoggedConnection(this.origin.connection(),this.logger,this.logStatements,this.query);
    }
}
