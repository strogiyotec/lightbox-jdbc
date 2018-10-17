package com.github.strogiyotec.lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import com.github.strogiyotec.lightbox.jdbc.value.data.CombinedParameters;
import org.apache.commons.lang3.StringUtils;
import org.jakarta.ChekedBiConsumer;
import org.jakarta.Text;

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
    private static final Pattern SQL_PATTERN = Pattern.compile("(?<!['\"]):([a-zA-Z0-9]*)(?!['\"])");

    /**
     * Sql format checker
     */
    private static final ChekedBiConsumer<Parameters, List<String>> checkedSql = new ValidSqlFormat();

    /**
     * Sql query
     */
    private final Callable<String> sql;

    public ParsedSqlQuery(final String text, Parameters values) {
        this(() -> text, values);
    }

    public ParsedSqlQuery(final String text, Parameter<?>... values) {
        this(() -> text, new CombinedParameters(values));
    }

    public ParsedSqlQuery(final Text text, Parameter<?>... values) {
        this(
                text,
                new CombinedParameters(values)
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
    private static final class ValidSqlFormat implements ChekedBiConsumer<Parameters, List<String>> {

        @Override
        public void accept(final Parameters values, final List<String> fields) throws Exception {
            checkRange(values.amount(), fields.size());
            int index = 0;
            for (final Parameter<?> val : values) {
                if (!val.name().equals(fields.get(index))) {
                    throw new IllegalArgumentException(
                            String.format(
                                    String.join(
                                            " ",
                                            "SQL #%d (%s) parameter is wrong",
                                            "or out of order"
                                    ),
                                    index + 1,
                                    val.name()
                            )
                    );
                }
                ++index;
            }
        }

        static void checkRange(final int valuesLength, final int fieldsLength) {
            if (valuesLength != fieldsLength) {
                throw new IllegalArgumentException(
                        String.join(
                                " ",
                                "SQL amount of fields and values",
                                "are different"
                        )
                );
            }
        }
    }
}
