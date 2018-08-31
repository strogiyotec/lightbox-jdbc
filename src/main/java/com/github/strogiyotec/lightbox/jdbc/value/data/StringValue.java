package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.DataValue;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public final class StringValue implements DataValue<String> {

    private final String name;

    private final String value;

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void prepare(final PreparedStatement statement, final int index) throws SQLException {
        statement.setString(index, this.value);
    }

    @Override
    public String get() {
        return this.value;
    }

    @Override
    public String asString() throws IOException {
        return this.value;
    }
}
