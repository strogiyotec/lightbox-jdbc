package com.github.strogiyotec.lightbox.jdbc;

import lombok.AllArgsConstructor;

import javax.json.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@AllArgsConstructor
public abstract class JsonResource implements JsonObject {

    /**
     * Resource
     */
    private final JsonObject resource;

    public JsonResource(final Supplier<JsonResource> resource) {
        this(
                resource.get()
        );
    }

    @Override
    public JsonArray getJsonArray(final String s) {
        return this.resource.getJsonArray(s);
    }

    @Override
    public JsonObject getJsonObject(final String s) {
        return this.resource.getJsonObject(s);
    }

    @Override
    public JsonNumber getJsonNumber(final String s) {
        return this.resource.getJsonNumber(s);
    }

    @Override
    public JsonString getJsonString(final String s) {
        return this.resource.getJsonString(s);
    }

    @Override
    public String getString(final String s) {
        return this.resource.getString(s);
    }

    @Override
    public String getString(final String s, final String s1) {
        return this.resource.getString(s, s1);
    }

    @Override
    public int getInt(final String s) {
        return this.resource.getInt(s);
    }

    @Override
    public int getInt(final String s, final int i) {
        return this.resource.getInt(s, i);
    }

    @Override
    public boolean getBoolean(final String s) {
        return this.resource.getBoolean(s);
    }

    @Override
    public boolean getBoolean(final String s, final boolean b) {
        return this.resource.getBoolean(s, b);
    }

    @Override
    public boolean isNull(final String s) {
        return this.resource.isNull(s);
    }

    @Override
    public ValueType getValueType() {
        return this.resource.getValueType();
    }

    @Override
    public int size() {
        return this.resource.size();
    }

    @Override
    public boolean isEmpty() {
        return this.resource.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.resource.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.resource.containsValue(value);
    }

    @Override
    public JsonValue get(final Object key) {
        return this.resource.get(key);
    }

    @Override
    public JsonValue put(final String key, final JsonValue value) {
        return this.resource.put(key, value);
    }

    @Override
    public JsonValue remove(final Object key) {
        return this.resource.remove(key);
    }

    @Override
    public void clear() {
        this.resource.clear();
    }

    @Override
    public void putAll(final Map<? extends String, ? extends JsonValue> m) {
        this.resource.putAll(m);
    }

    @Override
    public Set<String> keySet() {
        return this.resource.keySet();
    }

    @Override
    public Collection<JsonValue> values() {
        return this.resource.values();
    }

    @Override
    public Set<Entry<String, JsonValue>> entrySet() {
        return this.resource.entrySet();
    }
}
