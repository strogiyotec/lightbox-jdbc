package com.github.strogiyotec.lightbox.jdbc.script;

import com.github.strogiyotec.lightbox.jdbc.Script;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.session.TransactedSession;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Execute given scripts as single batch
 */
public final class BatchScript implements Script {

    private final List<String> scripts;

    private final Session session;

    public BatchScript(final List<String> scripts, final Session Session) {
        this.scripts = scripts;
        this.session = Session;
    }

    public BatchScript(final Session Session, final String... scripts) {
        this.scripts = Arrays.asList(scripts);
        this.session = Session;
    }

    public BatchScript(final Iterable<String> iterable, final Session Session) {
        this.scripts = Lists.newArrayList(iterable);
        this.session = Session;
    }

    public BatchScript(final String script, final Session Session) {
        this.scripts = Collections.singletonList(script);
        this.session = Session;
    }


    @Override
    public void execute() throws Exception {
        try (final Connection connection = new TransactedSession(this.session).connection()) {
            try (final Statement st = connection.createStatement()) {
                for (final String script : scripts) {
                    st.addBatch(script);
                }
                st.executeBatch();
            }
        }
    }
}
