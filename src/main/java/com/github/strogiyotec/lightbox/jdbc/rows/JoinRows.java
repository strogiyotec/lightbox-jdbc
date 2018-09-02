package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Tables;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public final class JoinRows implements Rows {

    private final List<Map<String, Object>> rows;

    public JoinRows(final ResultSet rs, final Tables tables) throws Exception {
        final ResultSetMetaData metaData = rs.getMetaData();
        final int columns = metaData.getColumnCount();
        int rows = 0;
        final List<Map<String, Object>> result = new ArrayList<>();
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
            if (!reduceByKeys(result, new HashMap<>(mainTable), tables)) {
                result.add(mainTable);
            }
        }
        this.rows = result;
    }

    @Override
    public Iterator<Map<String, Object>> iterator() {
        return this.rows.iterator();
    }

    private static boolean reduceByKeys(final List<Map<String, Object>> maps, final Map<String, Object> toAdd, final Tables tables) {
        boolean reduces = false;
        if (!maps.isEmpty()) {
            for (int i = 0; i < maps.size(); i++) {
                final Map<String, Object> map = maps.get(i);
                boolean rowsEquals = true;
                for (final String key : map.keySet()) {
                    if (!tables.support(key)) {
                        final Object o1 = map.get(key);
                        final Object o2 = toAdd.get(key);
                        if (!o1.equals(o2)) {
                            rowsEquals = false;
                        }
                        toAdd.remove(key);
                    }
                }
                if (rowsEquals) {
                    final List<String> names = tables.names();
                    for (final String name : names) {
                        final Object o = map.get(name);
                        if (o instanceof Map) {
                            final Map<String, Object> origin = (Map<String, Object>) map.get(name);
                            final Map<String, Object> newOne = (Map<String, Object>) toAdd.get(name);
                            map.put(name, Arrays.asList(origin, newOne));
                        } else {
                            final List<Map<String, Object>> origin = (List<Map<String, Object>>) map.get(name);
                            final Map<String, Object> newOne = (Map<String, Object>) toAdd.get(name);
                            origin.add(newOne);
                            map.put(name, origin);
                        }
                        reduces = true;
                    }
                }
            }
        }
        return reduces;
    }
}
