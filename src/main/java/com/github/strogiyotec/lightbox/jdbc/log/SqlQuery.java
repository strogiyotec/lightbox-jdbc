package com.github.strogiyotec.lightbox.jdbc.log;

import lombok.AllArgsConstructor;
import org.jakarta.UncheckedText;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public final class SqlQuery implements UncheckedText {

    private final List<Object> argc;

    private final String sql;

    private static final Pattern SQL_PARAMS_PATTERN = Pattern.compile("(\\?)|(:[^ ]+)");

    @Override
    public String asString() {
        final Matcher matcher = SQL_PARAMS_PATTERN.matcher(this.sql);
        final StringBuffer result = new StringBuffer(this.sql.length());
        int count = 0;
        while (matcher.find()) {
            matcher.appendReplacement(result, String.valueOf(this.argc.get(count++)));
        }
        return result.toString();
    }
}
