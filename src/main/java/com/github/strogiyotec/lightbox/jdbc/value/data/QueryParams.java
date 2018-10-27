package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Combined data params
 */
@AllArgsConstructor
public final class QueryParams implements Parameters {

    /**
     * Params
     */
    private final List<Parameter<?>> params;


    public QueryParams(Parameter<?>... values) {
        this(
                Arrays.asList(values)
        );
    }

    public QueryParams(Collection<Parameter<?>> values) {
        this(
                Collections.unmodifiableList(new ArrayList<>(values))
        );
    }

    @Override
    public PreparedStatement prepare(final PreparedStatement statement) throws SQLException {
        int index = 1;
        for (final Parameter<?> value : this.params) {
            value.prepare(statement, index);
            ++index;
        }
        return statement;

    }

    @Override
    public boolean contains(final String name, final int pos) {
        return this.params.get(pos).name().equals(name);
    }

    @Override
    public Iterator<Parameter<?>> iterator() {
        return this.params.iterator();
    }
}
