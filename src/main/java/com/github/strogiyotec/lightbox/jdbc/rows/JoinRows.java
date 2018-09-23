package com.github.strogiyotec.lightbox.jdbc.rows;

import com.github.strogiyotec.lightbox.jdbc.JoinedTables;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import org.jakarta.collections.MutableListOf;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public final class JoinRows implements Rows {

    private final List<Map<String, Object>> rows;

    public JoinRows(final ResultSet rs, final JoinedTables tables) throws Exception {
        final ResultSetMetaData metaData = rs.getMetaData();
        final int columns = metaData.getColumnCount();
        final List<Map<String, Object>> resultRows = new ArrayList<>();
        while (rs.next()) {
            final Map<String, Object> row = JoinRows.singleRow(columns, rs, metaData, tables);
            if (!JoinRows.shouldSkipRow(resultRows, new HashMap<>(row), tables)) {
                resultRows.add(row);
            }
        }
        this.rows = resultRows;
    }

    /**
     * This method retrieve field from database
     * Check does field belong tio joined tables
     * if so add this field to joined table,
     * Otherwise, add this field to row as single key value
     *
     * @param columns  columns number
     * @param rs       Result set
     * @param metaData Result set metadata
     * @param tables   Joined tables
     * @return singleRow with all fields
     * @throws SQLException if failed
     */
    private static Map<String, Object> singleRow(final int columns,
                                                 final ResultSet rs,
                                                 final ResultSetMetaData metaData,
                                                 final JoinedTables tables) throws SQLException {
        final Map<String, Object> row = new LinkedHashMap<>(columns, 1.0f);
        for (int i = 1; i <= columns; i++) {
            final String tableName = metaData.getTableName(i);
            final String fieldName = metaData.getColumnName(i);
            final Object fieldValue = rs.getObject(i);
            if (tables.contain(tableName)) {
                tables.add(row, fieldName, fieldValue, tableName);
            } else {
                row.put(fieldName, fieldValue);
            }
        }
        return row;
    }

    @Override
    public Iterator<Map<String, Object>> iterator() {
        return this.rows.iterator();
    }

    private static boolean shouldSkipRow(final List<Map<String, Object>> rows,
                                         final Map<String, Object> newRow,
                                         final JoinedTables tables) {
        boolean shouldSkip = false;
        if (!rows.isEmpty()) {
            for (int i = 0; i < rows.size(); i++) {
                final Map<String, Object> oldRow = rows.get(i);
                final boolean rowsEquals = JoinRows.rowsEquals(oldRow, tables, newRow);
                if (rowsEquals) {
                    shouldSkip = true;
                    JoinRows.addJoinedRow(tables, oldRow, newRow);
                }
            }
        }
        return shouldSkip;
    }

    /**
     * This method retrieve each key from oldRow's keySet,
     * if key is not joined table name then compare value of this key
     * from both rows, if they are equals remove this key from newRow,
     * so in the end newRow will contain only joined tables
     * comparing each value from both rows,
     * if they equals , remove field from newRow
     * If rows are equals , newRow will contain only
     *
     * @param oldRow old oldRow
     * @param tables Joined tables
     * @param newRow new oldRow
     * @return true if old row and new Rows are equal
     */
    private static boolean rowsEquals(final Map<String, Object> oldRow, final JoinedTables tables, final Map<String, Object> newRow) {
        boolean rowsEquals = true;
        for (final String key : oldRow.keySet()) {
            if (!tables.contain(key)) {
                final Object o1 = oldRow.get(key);
                final Object o2 = newRow.get(key);
                if (!o1.equals(o2)) {
                    rowsEquals = false;
                    break;
                } else {
                    newRow.remove(key);
                }
            }
        }
        return rowsEquals;
    }

    /**
     * This method get joined table map from oldRow
     * and check is it single map , if so add joined table map from new row to old row,
     * otherwise retrieve list of joined table maps and add new map from newRow to this list
     * Method add new joined table only when values from new table are not present in old joined tables
     *
     * @param tables Joined tables
     * @param oldRow old row
     * @param newRow new row
     */
    private static void addJoinedRow(final JoinedTables tables, final Map<String, Object> oldRow, final Map<String, Object> newRow) {
        final List<String> names = tables.names();
        for (final String name : names) {
            final Object field = oldRow.get(name);
            if (field instanceof Map) {
                final Map<String, Object> origin = (Map<String, Object>) field;
                final Map<String, Object> newOne = (Map<String, Object>) newRow.get(name);
                if (!origin.equals(newOne)) {
                    oldRow.put(name, new MutableListOf<>(origin, newOne));
                }
            } else if (field instanceof List) {
                final List<Map<String, Object>> origin = (List<Map<String, Object>>) field;
                final Map<String, Object> newOne = (Map<String, Object>) newRow.get(name);
                if (origin.stream().noneMatch(row -> row.equals(newOne))) {
                    origin.add(newOne);
                    oldRow.put(name, origin);
                }
            }
        }
    }


}
