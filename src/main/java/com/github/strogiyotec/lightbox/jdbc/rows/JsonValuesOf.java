package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Rows;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

/**
 * JsonArray from Rows
 */
public final class JsonValuesOf extends JsonArrayResource {

    public JsonValuesOf(final Rows rows) {
        super((Supplier<JsonArray>) () -> {
            final Iterator<Map<String, Object>> iterator = rows.iterator();
            final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (iterator.hasNext()) {
                arrayBuilder.add(Json.createObjectBuilder(iterator.next()));
            }
            return arrayBuilder.build();
        });
    }

    public JsonValuesOf(final Iterator<Map<String, Object>> rows) {
        super((Supplier<JsonArray>) () -> {
            final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rows.hasNext()) {
                arrayBuilder.add(Json.createObjectBuilder(rows.next()));
            }
            return arrayBuilder.build();
        });
    }

}
