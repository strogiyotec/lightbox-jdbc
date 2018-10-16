package com.github.strogiyotec.lightbox.jdbc.value.data;

import com.github.strogiyotec.lightbox.jdbc.DataValue;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValueOf;

import javax.json.JsonObject;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Json val.
 */
public final class JsonValue implements DataValue<JsonObject> {

    /**
     * Name
     */
    private final String name;

    /**
     * Value
     */
    private final JsonObject value;

    public JsonValue(final String name, final JsonObject origin) {
        this.value = origin;
        this.name = name;
    }

    public JsonValue(final String name, final String str) {
        this.value = new JsonValueOf(str);
        this.name = name;
    }

    public JsonValue(final String name, final Map<String, Object> map) {
        this.value = new JsonValueOf(map);
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void prepare(final PreparedStatement statement, final int index) throws SQLException {
        statement.setString(index, this.value.toString());
    }

    @Override
    public JsonObject get() {
        return this.value;
    }

    @Override
    public String asString() throws IOException {
        return this.value.toString();
    }
}
