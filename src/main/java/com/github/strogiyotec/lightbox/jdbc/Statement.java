package com.github.strogiyotec.lightbox.jdbc;

/**
 * Sql statement
 *
 * @param <T> Type of the result
 */
public interface Statement<T> {

    /**
     * @return A Result of a type
     * @throws Exception If fails
     */
    Result<T> result() throws Exception;
}
