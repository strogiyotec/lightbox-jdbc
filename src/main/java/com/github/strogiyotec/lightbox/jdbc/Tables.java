package com.github.strogiyotec.lightbox.jdbc;

import java.util.List;
import java.util.Map;


public interface Tables {

    /**
     * @param name of table
     * @return true if impl support Table with given name
     */
    boolean support(String name);

    List<String> names();

    void add(Map<String, Object> base, String fieldName, Object field, String tableName, int rowNumber);
}
