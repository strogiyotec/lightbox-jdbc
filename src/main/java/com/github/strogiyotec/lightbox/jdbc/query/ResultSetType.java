package com.github.strogiyotec.lightbox.jdbc.query;

import java.sql.ResultSet;

public enum ResultSetType {
    FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY),
    SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE),
    SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE);

    final int type;

    ResultSetType(final int type) {
        this.type = type;
    }

    public int value() {
        return this.type;
    }

}
