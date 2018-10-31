package com.github.strogiyotec.lightbox.jdbc.bind;

import java.lang.reflect.Method;
import java.util.function.Predicate;

/**
 * Check that given method is declared in {@link Object}
 */
final class MethodOfObject implements Predicate<Method> {
    @Override
    public boolean test(final Method method) {
        if (method == null) {
            return false;
        }
        try {
            Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
            return true;
        } catch (final Exception ex) {
            return false;
        }

    }
}
