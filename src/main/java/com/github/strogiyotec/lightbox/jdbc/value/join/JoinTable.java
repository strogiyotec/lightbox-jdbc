package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.Table;

import java.util.Arrays;
import java.util.List;

public final class JoinTable implements Table {

    private final String name;

    private final List<String> joinBy;

    public JoinTable(final String name, final List<String> joinBy) {
        this.name = name;
        this.joinBy = joinBy;
    }

    public JoinTable(final CharSequence name, final String... joinBy) {
        this.name = name.toString();
        this.joinBy = Arrays.asList(joinBy);
    }

    @Override
    public String name() {
        return this.name;
    }
}
