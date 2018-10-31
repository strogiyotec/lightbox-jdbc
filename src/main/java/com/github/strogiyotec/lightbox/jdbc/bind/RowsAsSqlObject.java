package com.github.strogiyotec.lightbox.jdbc.bind;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * This class map given rows to SqlObject
 * //TODO implement all non primitive types such as jsonb, int[]
 *
 * @param <T> SqlObject class type
 */
public final class RowsAsSqlObject<T extends SqlObject> implements Supplier<T> {
    /**
     * Represents rows of table
     */
    private final Map<String, Object> rows;

    /**
     * SqlObject class
     */
    private final Class<T> clazz;

    /**
     * Apply function to each extracted column value,
     * by default check on null, if so @throw {@link IllegalStateException}
     */
    private final BiFunction<Object, Column, Object> mapper;

    public RowsAsSqlObject(final Map<String, Object> rows, final Class<T> clazz) {
        this.rows = rows;
        this.clazz = clazz;
        this.mapper = new SkipNullValue();
    }

    public RowsAsSqlObject(final Map<String, Object> rows, final Class<T> clazz, final BiFunction<Object, Column, Object> mapper) {
        this.rows = rows;
        this.clazz = clazz;
        this.mapper = mapper;
    }

    public RowsAsSqlObject(final Statement<Rows> result, final Class<T> clazz) throws Exception {
        this.rows = result.result().call().iterator().next();
        this.clazz = clazz;
        this.mapper = new SkipNullValue();
    }

    public RowsAsSqlObject(final Statement<Rows> result, final Class<T> clazz, final BiFunction<Object, Column, Object> mapper) throws Exception {
        this.rows = result.result().call().iterator().next();
        this.clazz = clazz;
        this.mapper = mapper;
    }

    @Override
    public T get() {
        if (this.clazz.isInterface()) {
            final Object newInstance = Proxy.newProxyInstance(this.clazz.getClassLoader(), new Class[]{this.clazz}, (proxy, method, args) -> {
                if (method.isAnnotationPresent(Column.class)) {
                    final Column column = method.getAnnotation(Column.class);
                    final Object result = RowsAsSqlObject.this.rows.get(column.name());
                    return this.mapper.apply(result, column);
                } else {
                    return method.invoke(proxy, args);
                }
            });
            return (T) newInstance;
        } else {
            throw new IllegalStateException("Can't map non interface type");
        }
    }

    private static class SkipNullValue implements BiFunction<Object, Column, Object> {

        @Override
        public Object apply(final Object columnValue, final Column column) {
            if (columnValue == null && !column.nullable()) {
                throw new IllegalStateException(
                        String.format(
                                "Column for column with name %s is null",
                                column.name()
                        )
                );
            }
            return columnValue;
        }
    }
}
