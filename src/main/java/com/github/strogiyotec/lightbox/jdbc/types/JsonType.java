package com.github.strogiyotec.lightbox.jdbc.types;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.function.Function;

public final class JsonType implements Function<Object, JsonObject> {

    @Override
    public JsonObject apply(final Object o) {
        try (final JsonReader reader = Json.createReader(new StringReader(o.toString()))) {
            return reader.readObject();
        }
    }
}
