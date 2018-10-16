package com.github.strogiyotec.lightbox.jdbc.types;

import java.util.function.Function;
import java.util.stream.Stream;

public final class IntArrayType implements Function<Object, int[]> {
    @Override
    public int[] apply(final Object o) {
        final String strValue = o.toString().replaceAll("[^\\d]", " ").trim();
        return Stream.of(strValue.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
