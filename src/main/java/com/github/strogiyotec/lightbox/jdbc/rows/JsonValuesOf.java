package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Tables;

import javax.json.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

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

    public JsonValuesOf(final ResultSet rs, final Tables<JsonArray> tables) throws Exception {
        super((Callable<JsonArray>) () -> {
            final ResultSetMetaData metaData = rs.getMetaData();
            final int columns = metaData.getColumnCount();
            int rows = 0;
            final JsonArrayBuilder result = Json.createArrayBuilder();
            while (rs.next()) {
                final Map<String, Object> mainTable = new HashMap<>(columns, 1.0f);
                for (int i = 1; i <= columns; i++) {
                    final String tableName = metaData.getTableName(i);
                    final String fieldName = metaData.getColumnName(i);
                    final Object field = rs.getObject(i);
                    if (tables.support(tableName)) {
                        tables.add(mainTable, fieldName, field, tableName, rows);
                    } else {
                        mainTable.put(fieldName, field);
                    }
                }
                rows++;
                Json.createObjectBuilder(mainTable).build();
                result.add(Json.createObjectBuilder(mainTable).build());
            }

            return result.build();
        });
    }

}
