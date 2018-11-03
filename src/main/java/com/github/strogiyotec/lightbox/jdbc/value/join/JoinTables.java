package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.Tables;
import org.jakarta.collections.MutableMapOf;

import java.util.*;

public final class JoinTables implements Tables {

    /**
     * List of tables names
     */
    private final List<String> names;

    public JoinTables(final List<String> tables) {
        this.names = Collections.unmodifiableList(tables);
    }

    public JoinTables(final String... tables) {
        this.names = Arrays.asList(tables);
    }


    @Override
    public boolean contain(final String name) {
        return this.names.stream().anyMatch(table -> table.equals(name));
    }

    @Override
    public List<String> names() {
        return new ArrayList<>(this.names);
    }

    /**
     * This method check if mainTable contains joined table
     * if so get map which represent joined table and add new fieldName and fieldValue to this map
     * otherwise , create new {@link MutableMapOf} with given fieldName and fieldValue and add this map
     * to mainTable
     *
     * @param mainTable       table which is mainTable
     * @param fieldName       field name
     * @param fieldValue      field Value
     * @param joinedTableName joined table name
     */
    @Override
    public void add(final Map<String, Object> mainTable,
                    final String fieldName,
                    final Object fieldValue,
                    final String joinedTableName) {
        final Map<String, Object> joinedTable;
        if (!mainTable.containsKey(joinedTableName)) {
            joinedTable = new MutableMapOf<>(fieldName, fieldValue);
        } else {
            joinedTable = (Map<String, Object>) mainTable.get(joinedTableName);
            joinedTable.put(fieldName, fieldValue);
        }
        mainTable.put(joinedTableName, joinedTable);
    }

}
