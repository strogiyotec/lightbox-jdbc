package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.JsonResource;
import com.github.strogiyotec.lightbox.jdbc.Rows;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.Iterator;
import java.util.Map;

public final class JsonValuesOf extends JsonResource {

    public JsonValuesOf(final Rows rows, final String key) throws Exception {
        super(() -> {
            final Iterator<Map<String, Object>> iterator = rows.iterator();
            final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (iterator.hasNext()) {
                arrayBuilder.add(Json.createObjectBuilder(iterator.next()));
            }
            return Json.createObjectBuilder().add(key, arrayBuilder).build();
        });
    }
}
