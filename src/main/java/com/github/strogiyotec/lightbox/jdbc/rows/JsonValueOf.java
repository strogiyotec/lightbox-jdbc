package com.github.strogiyotec.lightbox.jdbc.rows;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Map;
import java.util.function.Supplier;


public final class JsonValueOf extends JsonResource {

    public JsonValueOf(final Map<String, Object> rows) {
        super((Supplier<JsonObject>) () -> Json.createObjectBuilder(rows).build());
    }

}