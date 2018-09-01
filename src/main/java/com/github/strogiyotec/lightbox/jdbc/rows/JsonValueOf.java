package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Tables;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;


public final class JsonValueOf extends JsonResource {

    public JsonValueOf(final Map<String, Object> rows) {
        super((Supplier<JsonObject>) () -> Json.createObjectBuilder(rows).build());
    }

}