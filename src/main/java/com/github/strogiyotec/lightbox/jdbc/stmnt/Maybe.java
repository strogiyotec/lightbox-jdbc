package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Specify single row from db
 * Return instance of Empty map if no such row
 */
public final class Maybe implements Callable<Map<String, Object>> {

    private final Map<String, Object> row;

    public Maybe(final Statement<Rows> rows) throws Exception {
        final Iterator<Map<String, Object>> iterator = rows.result().call().iterator();
        if (iterator.hasNext()) {
            this.row = iterator.next();
        } else {
            this.row = Collections.emptyMap();
        }
    }

    @Override
    public Map<String, Object> call() throws Exception {
        return this.row;
    }
}
