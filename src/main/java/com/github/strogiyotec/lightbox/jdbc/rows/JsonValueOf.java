package com.github.strogiyotec.lightbox.jdbc.rows;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.Map;
import java.util.function.Supplier;


/**
 * JsonObject from Map
 */
public final class JsonValueOf extends JsonResource {

    public JsonValueOf(final Map<String, Object> rows) {
        super((Supplier<JsonObject>) () -> Json.createObjectBuilder(rows).build());
    }

    public JsonValueOf(final String value) {
        super(Json.createParser(new StringReader(value)).getObject());
    }

}