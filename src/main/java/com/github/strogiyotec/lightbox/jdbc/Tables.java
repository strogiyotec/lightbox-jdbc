package com.github.strogiyotec.lightbox.jdbc;

import java.util.List;
import java.util.Map;


public interface Tables {


    /**
     * @param name of table
     * @return true if contains Table with given name
     */
    boolean contain(String name);

    /**
     * @return list of tables names
     */
    List<String> names();

    /**
     * Add field to table inside another table (For example if you use join)
     *
     * @param mainTable       table which is mainTable
     * @param fieldName       field name
     * @param fieldValue      field Column
     * @param joinedTableName joined table name
     */
    void add(Map<String, Object> mainTable, String fieldName, Object fieldValue, String joinedTableName);
}
