package com.github.strogiyotec.lightbox.jdbc;

import java.util.List;

public interface Table {

    /**
     * @return name of joined table
     */
    String name();

    List<String> joinBy();
}
