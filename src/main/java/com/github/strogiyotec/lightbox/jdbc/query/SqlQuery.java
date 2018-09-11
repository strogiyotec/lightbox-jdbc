package com.github.strogiyotec.lightbox.jdbc.query;

import org.jakarta.Text;
import org.jakarta.UncheckedText;
import org.jakarta.text.UncheckedTextOf;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SqlQuery implements UncheckedText {

    private final List<Object> argc;

    private final String sql;

    public SqlQuery(final List<Object> argc, final String sql) {
        this.argc = argc;
        this.sql = sql;
    }

    public SqlQuery(final List<Object> argc, final UncheckedText sql) {
        this.argc = argc;
        this.sql = sql.asString();
    }

    public SqlQuery(final List<Object> argc, final CharSequence sql) {
        this.argc = argc;
        this.sql = sql.toString();
    }

    public SqlQuery(final List<Object> argc, final Text sql) {
        this.argc = argc;
        this.sql = new UncheckedTextOf(sql).asString();
    }

    public SqlQuery(final String sql, final Object... argc) {
        this(
                Arrays.asList(argc),
                sql
        );
    }

    private static final Pattern SQL_PARAMS_PATTERN = Pattern.compile("(\\?)|(:[^ ]+)");

    @Override
    public String asString() {
        final StringBuffer result = new StringBuffer(this.sql.length());
        if (SQL_PARAMS_PATTERN.matcher(this.sql).find()) {
            final Matcher matcher = SQL_PARAMS_PATTERN.matcher(this.sql);
            int count = 0;
            while (matcher.find()) {
                matcher.appendReplacement(result, String.valueOf(this.argc.get(count++)));
            }
        } else {
            result.append(this.sql);
        }
        return result.toString();
    }
}
