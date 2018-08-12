package com.github.strogiyotec.lightbox.jdbc.script;

import com.github.strogiyotec.lightbox.jdbc.Script;
import com.github.strogiyotec.lightbox.jdbc.Session;
import lombok.AllArgsConstructor;

import java.io.InputStream;

@AllArgsConstructor
public final class SqlScript implements Script {

    private final Session session;

    private final InputStream source;

    @Override
    public void execute() throws Exception {

    }
}
