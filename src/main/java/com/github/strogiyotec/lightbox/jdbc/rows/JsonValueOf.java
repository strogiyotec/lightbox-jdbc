package com.github.strogiyotec.lightbox.jdbc.rows;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
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
        super((Supplier<JsonObject>) () -> {
            final JsonParser parser = Json.createParser(new StringReader(value));
            parser.next();
            return parser.getObject();
        });
    }

}