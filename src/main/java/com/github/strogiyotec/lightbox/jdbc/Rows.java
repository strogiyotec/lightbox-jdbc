package com.github.strogiyotec.lightbox.jdbc;

import java.io.Serializable;
import java.util.Map;

/**
 * Represent a set of name and values retrieved from DB
 */
public interface Rows extends Iterable<Map<String, Object>>, Serializable {

}
