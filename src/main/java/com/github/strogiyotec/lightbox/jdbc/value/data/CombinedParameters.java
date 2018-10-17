package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.Parameter;
import com.github.strogiyotec.lightbox.jdbc.Parameters;
import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Combined data values
 */
@AllArgsConstructor
public final class CombinedParameters implements Parameters {

    /**
     * Values
     */
    private final List<Parameter<?>> values;


    public CombinedParameters(Parameter<?>... values) {
        this(
                Arrays.asList(values)
        );
    }

    public CombinedParameters(Collection<Parameter<?>> values) {
        this(
                Collections.unmodifiableList(new ArrayList<>(values))
        );
    }

    @Override
    public Parameters with(final Parameter<?> value) {
        this.values.add(value);
        return this;
    }

    @Override
    public PreparedStatement prepare(final PreparedStatement statement) throws SQLException {
        int index = 1;
        for (final Parameter<?> value : this.values) {
            value.prepare(statement, index);
            ++index;
        }
        return statement;

    }

    @Override
    public int amount() {
        return this.values.size();
    }

    @Override
    public Iterator<Parameter<?>> iterator() {
        return this.values.iterator();
    }
}
