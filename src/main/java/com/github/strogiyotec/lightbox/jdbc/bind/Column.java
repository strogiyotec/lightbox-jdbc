package com.github.strogiyotec.lightbox.jdbc.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Column {
    /**
     * @return name of the column
     */
    String name();

    /**
     * @return true if value can by null
     * false otherwise
     */
    boolean nullable() default false;

}
