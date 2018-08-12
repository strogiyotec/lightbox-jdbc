package com.github.strogiyotec.lightbox.jdbc.value;

import com.github.strogiyotec.lightbox.jdbc.DataValue;
import com.github.strogiyotec.lightbox.jdbc.DataValues;
import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Combined data values
 */
@AllArgsConstructor
public final class CombinedDataValues implements DataValues {

    /**
     * Values
     */
    private final List<DataValue<?>> values;


    public CombinedDataValues(DataValue<?>... values) {
        this(
                Arrays.asList(values)
        );
    }

    public CombinedDataValues(Collection<DataValue<?>> values) {
        this(
                Collections.unmodifiableList(new ArrayList<>(values))
        );
    }

    @Override
    public DataValues with(final DataValue<?> value) {
        this.values.add(value);
        return this;
    }

    @Override
    public PreparedStatement prepare(final PreparedStatement statement) throws SQLException {
        int index = 1;
        for (final DataValue<?> value : this.values) {
            value.prepare(statement, index);
            ++index;
        }
        return statement;

    }

    @Override
    public Iterator<DataValue<?>> iterator() {
        return this.values.iterator();
    }
}
