package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.Table;
import com.github.strogiyotec.lightbox.jdbc.Tables;
import org.jakarta.collections.MutableMapOf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class JoinTables implements Tables {

    private final List<Table> tables;

    private final List<String> names;

    public JoinTables(final List<Table> tables) {
        this.tables = tables;
        this.names = this.tables.stream().map(Table::name).collect(Collectors.toList());
    }

    public JoinTables(final Table... tables) {
        this.tables = Arrays.asList(tables);
        this.names = this.tables.stream().map(Table::name).collect(Collectors.toList());
    }


    @Override
    public boolean support(final String name) {
        return this.tables.stream().anyMatch(table -> table.name().equals(name));
    }

    @Override
    public List<String> names() {
        return this.names;
    }

    @Override
    public void add(final Map<String, Object> baseTable, final String fieldName, final Object field, final String tableName, int rowNumber) {
        final Map<String, Object> joinedTables;
        if (!baseTable.containsKey(tableName)) {
            joinedTables = new MutableMapOf<>(fieldName, field);
        } else {
            joinedTables = (Map<String, Object>) baseTable.get(tableName);
            joinedTables.put(fieldName, field);
        }
        baseTable.put(tableName, joinedTables);
    }

}
