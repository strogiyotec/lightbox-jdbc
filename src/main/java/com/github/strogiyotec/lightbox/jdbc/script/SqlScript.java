package com.github.strogiyotec.lightbox.jdbc.script;

import com.github.strogiyotec.lightbox.jdbc.Script;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Execute given scripts as separate query
 */
public final class SqlScript implements Script {

    private final List<String> scripts;

    private final Session session;

    public SqlScript(final List<String> scripts, final Session Session) {
        this.scripts = scripts;
        this.session = Session;
    }

    public SqlScript(final Session Session, final String... scripts) {
        this.scripts = Arrays.asList(scripts);
        this.session = Session;
    }

    public SqlScript(final Iterable<String> iterable, final Session Session) {
        this.scripts = Lists.newArrayList(iterable);
        this.session = Session;
    }

    public SqlScript(final String script, final Session Session) {
        this.scripts = Collections.singletonList(script);
        this.session = Session;
    }


    @Override
    public void execute() throws Exception {
        for (final String script : scripts) {
            new Update(
                    this.session,
                    new SimpleQuery(script)
            ).result();
        }
    }
}
