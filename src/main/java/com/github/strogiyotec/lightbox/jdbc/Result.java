package com.github.strogiyotec.lightbox.jdbc;

import org.jakarta.CheckedSupplier;

public interface Result<T> extends CheckedSupplier<T> {

    /**
     * Empty result set
     *
     * @param <T> type
     */
    public final class EmptyResult<T> implements Result<T> {

        @Override
        public T get() throws Exception {
            throw new UnsupportedOperationException("EmptyResult doesn't support #get()");
        }
    }
}
