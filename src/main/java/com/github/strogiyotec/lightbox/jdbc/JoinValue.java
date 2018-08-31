package com.github.strogiyotec.lightbox.jdbc;

import java.util.Collection;

public interface JoinValue {

    /**
     *
     * @return typ of collection
     */
    Class<? extends Collection> type();

    /**
     *
     * @return name of joined table
     */
    String name();
}
