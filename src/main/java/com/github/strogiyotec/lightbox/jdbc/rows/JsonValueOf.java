package com.github.strogiyotec.lightbox.jdbc.rows;

import javax.json.Json;
import java.util.Map;


public final class JsonValueOf extends JsonResource {

    public JsonValueOf(final Map<String, Object> rows) {
        super(() -> Json.createObjectBuilder(rows).build());
    }
}