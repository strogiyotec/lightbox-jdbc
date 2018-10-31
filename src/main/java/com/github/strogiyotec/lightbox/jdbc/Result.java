package com.github.strogiyotec.lightbox.jdbc;

import java.util.concurrent.Callable;

public interface Result<T> extends Callable<T> {

    /**
     * Empty result set
     *
     * @param <T> type
     */
    final class EmptyResult<T> implements Result<T> {

        @Override
        public T call() throws Exception {
            throw new UnsupportedOperationException("EmptyResult doesn't support #get()");
        }
    }
}
