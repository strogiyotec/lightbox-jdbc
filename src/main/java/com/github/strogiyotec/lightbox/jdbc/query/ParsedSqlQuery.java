package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import com.github.strogiyotec.lightbox.jdbc.value.data.QueryParams;
import org.apache.commons.lang3.StringUtils;
import org.jakarta.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse given sql
 */
public final class ParsedSqlQuery implements Text {

    /**
     * sql pattern
     */
    private static final Pattern SQL_PATTERN = Pattern.compile("(?<!['\"]):([a-zA-Z0-9]*)(?!['\"])");

    /**
     * Sql format checker
     */
    private static final BiConsumer<Parameters, List<String>> checkedSql = new ValidSqlFormat();

    /**
     * Sql query
     */
    private final Callable<String> sql;

    public ParsedSqlQuery(final String text, Parameters values) {
        this(() -> text, values);
    }

    public ParsedSqlQuery(final String text, Parameter<?>... values) {
        this(() -> text, new QueryParams(values));
    }

    public ParsedSqlQuery(final Text text, Parameter<?>... values) {
        this(
                text,
                new QueryParams(values)
        );
    }

    public ParsedSqlQuery(final Text text, Parameters values) {
        this.sql = () -> {
            final String origin = text.asString();
            final List<String> list = combineSqlMatchers(origin);
            checkedSql.accept(values, list);
            return origin.replaceAll(SQL_PATTERN.pattern(), "?");
        };
    }

    /**
     * Combine matchers from given string
     *
     * @param str Sql
     * @return List of all matched patterns
     */
    private static List<String> combineSqlMatchers(final String str) {
        final List<String> fields = new ArrayList<>(StringUtils.countMatches(str, ':'));
        final Matcher matcher = SQL_PATTERN.matcher(str);
        while (matcher.find()) {
            fields.add(matcher.group(1));
        }
        return fields;
    }

    @Override
    public String asString() throws Exception {
        return this.sql.call();
    }

    /**
     * Validate given Sql
     */
    private static final class ValidSqlFormat implements BiConsumer<Parameters, List<String>> {

        @Override
        public void accept(final Parameters params, final List<String> fields) {
            int index = 0;
            for (int i = 0; i < fields.size(); i++) {
                if (!params.contains(fields.get(i), i)) {
                    throw new IllegalArgumentException(
                            String.format(
                                    "SQL parameter #%d is wrong or out of order",
                                    index + 1
                            )
                    );
                }
            }
        }
    }
}
