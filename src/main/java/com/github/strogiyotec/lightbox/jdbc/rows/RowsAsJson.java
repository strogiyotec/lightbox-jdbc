package com.github.strogiyotec.lightbox.jdbc.rows;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;

public final class RowsAsJson implements JsonObject{
    @Override
    public JsonArray getJsonArray(final String s) {
        return null;
    }

    @Override
    public JsonObject getJsonObject(final String s) {
        return null;
    }

    @Override
    public JsonNumber getJsonNumber(final String s) {
        return null;
    }

    @Override
    public JsonString getJsonString(final String s) {
        return null;
    }

    @Override
    public String getString(final String s) {
        return null;
    }

    @Override
    public String getString(final String s, final String s1) {
        return null;
    }

    @Override
    public int getInt(final String s) {
        return 0;
    }

    @Override
    public int getInt(final String s, final int i) {
        return 0;
    }

    @Override
    public boolean getBoolean(final String s) {
        return false;
    }

    @Override
    public boolean getBoolean(final String s, final boolean b) {
        return false;
    }

    @Override
    public boolean isNull(final String s) {
        return false;
    }
}
