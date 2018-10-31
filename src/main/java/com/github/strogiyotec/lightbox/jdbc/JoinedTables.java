package com.github.strogiyotec.lightbox.jdbc;

import java.util.List;
import java.util.Map;


public interface JoinedTables {

    /**
     * @param name of table
     * @return true if contain Table with given name
     */
    boolean contain(String name);

    /**
     * @return names of joined tables
     */
    List<String> names();

    /**
     * This method add field of the joined table to mainTable table
     * Data structure of joined table field depend on implementation
     *
     * @param mainTable       table which is mainTable
     * @param fieldName       field name
     * @param fieldValue      field Column
     * @param joinedTableName joined table name
     */
    void add(Map<String, Object> mainTable, String fieldName, Object fieldValue, String joinedTableName);
}
