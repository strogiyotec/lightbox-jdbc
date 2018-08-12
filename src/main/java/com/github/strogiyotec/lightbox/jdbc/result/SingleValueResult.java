package com.github.strogiyotec.lightbox.jdbc.result;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class SingleValueResult<T> implements Result<T> {

    /**
     * Statement
     */
    private final Statement<T> statement;

    /**
     * Type value to return
     */
    private final Class<T> type;

    @Override
    public T get() throws Exception {
        return this.type.cast(this.statement.result().get());
    }
}
