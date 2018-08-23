package com.github.strogiyotec.lightbox.jdbc.query;

public enum ResultSetType {
    FORWARD_ONLY(1003),
    SCROLL_INSENSITIVE(1004),
    SCROLL_SENSITIVE(1005);

    final int type;

    ResultSetType(final int type) {
        this.type = type;
    }

    public int value() {
        return this.type;
    }

}
