package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.DataValue;
import com.github.strogiyotec.lightbox.jdbc.DataValues;
import com.github.strogiyotec.lightbox.jdbc.value.data.CombinedDataValues;
import org.apache.commons.lang3.StringUtils;
import org.jakarta.ChekedBiConsumer;
import org.jakarta.Text;
import org.jakarta.text.JoinedText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse given sql
 */
public final class ParsedSqlQuery implements Text {

    /**
     * sql pattern
     */
    private static final Pattern SQL_PATTERN = Pattern.compile("(?<!'):([\\w]*)(?!')");
    /**
     * Sql format checker
     */
    private static final ChekedBiConsumer<DataValues, List<String>> checkedSql = new ValidSqlFormat();
    /**
     * Sql query
     */
    private final Callable<String> sql;

    public ParsedSqlQuery(final String text, DataValues values) {
        this(() -> text, values);
    }

    public ParsedSqlQuery(final String text, DataValue<?>... values) {
        this(() -> text, new CombinedDataValues(values));
    }

    public ParsedSqlQuery(final Text text, DataValue<?>... values) {
        this(
                text,
                new CombinedDataValues(values)
        );
    }

    public ParsedSqlQuery(final Text text, DataValues values) {
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
    public String asString() {
        try {
            return this.sql.call();
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String toString() {
        return this.asString();
    }

    /**
     * Validate given Sql
     */
    private static final class ValidSqlFormat implements ChekedBiConsumer<DataValues, List<String>> {

        @Override
        public void accept(final DataValues values, final List<String> fields) throws Exception {
            int index = 0;
            for (final DataValue<?> val : values) {
                if (!val.name().equals(fields.get(index))) {
                    throw new IllegalArgumentException(
                            String.format(
                                    new JoinedText(
                                            " ",
                                            "SQL #%d (%s) parameter is wrong",
                                            "or out of order"
                                    ).asString(),
                                    index + 1,
                                    val.name()
                            )
                    );
                }
                ++index;
            }
        }
    }

}
