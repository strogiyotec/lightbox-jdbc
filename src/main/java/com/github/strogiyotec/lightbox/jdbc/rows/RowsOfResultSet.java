package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.Rows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Rows from {@link ResultSet}
 */
public final class RowsOfResultSet implements Rows {

    /**
     * DB rows
     */
    private final List<Map<String, Object>> rows;

    public RowsOfResultSet(final ResultSet resultSet) throws SQLException {
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int cols = metaData.getColumnCount();
        final List<Map<String, Object>> rows = new LinkedList<>();
        while (resultSet.next()) {
            final Map<String, Object> fields = new LinkedHashMap<>(cols);
            for (int i = 1; i <= cols; ++i) {
                fields.put(
                        metaData.getColumnName(i),
                        resultSet.getObject(i)
                );
            }
            rows.add(fields);
        }
        this.rows = rows;
    }


    @Override
    public Iterator<Map<String, Object>> iterator() {
        return this.rows.iterator();
    }
}
