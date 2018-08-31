package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.JoinValue;

import java.util.Collection;
import java.util.Set;

/**
 * Join value as set
 */
public final class SetJoinValue implements JoinValue{

    private final String name;

    private final Class<? extends Set> clazz;

    public SetJoinValue(final String name, final Class<? extends Set> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public Class<? extends Collection> type() {
        return this.clazz;
    }

    @Override
    public String name() {
        return this.name;
    }
}
