package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.JsonResource;

import javax.json.Json;
import java.util.Map;


public final class JsonValueOf extends JsonResource {

    public JsonValueOf(final Map<String, Object> rows) throws Exception {
        super(() -> Json.createObjectBuilder(rows).build());
    }
}