package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.Table;

public final class JoinTable implements Table {

    private final String name;

    public JoinTable(final String name) {
        this.name = name;
    }

    public JoinTable(final CharSequence name, final String... joinBy) {
        this.name = name.toString();
    }

    @Override
    public String name() {
        return this.name;
    }
}
