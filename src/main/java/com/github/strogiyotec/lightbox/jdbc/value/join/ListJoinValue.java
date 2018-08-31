package com.github.strogiyotec.lightbox.jdbc.value.join;

import com.github.strogiyotec.lightbox.jdbc.JoinValue;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * List join value
 */
@AllArgsConstructor
public final class ListJoinValue implements JoinValue{

    private final String name;

    private final Class<? extends List> clazz;

    @Override
    public Class<? extends Collection> type() {
        return this.clazz;
    }

    @Override
    public String name() {
        return this.name;
    }
}
