package com.github.strogiyotec.lightbox.jdbc.rows;

import lombok.EqualsAndHashCode;

import javax.json.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;


@EqualsAndHashCode(of = "resource")
public abstract class JsonResource implements JsonObject {

    /**
     * The JsonObject resource in question.
     */
    private final JsonObject resource;

    /**
     * Ctor.
     *
     * @param resource Supply the JsonObject.
     */
    public JsonResource(final Supplier<JsonObject> resource) {
        this(resource.get());
    }


    public JsonResource(final JsonObject resource) {
        this.resource = resource;
    }

    @Override
    public JsonArray getJsonArray(final String name) {
        return this.resource.getJsonArray(name);
    }

    @Override
    public JsonObject getJsonObject(final String name) {
        return this.resource.getJsonObject(name);
    }

    @Override
    public JsonNumber getJsonNumber(final String name) {
        return this.resource.getJsonNumber(name);
    }

    @Override
    public JsonString getJsonString(final String name) {
        return this.resource.getJsonString(name);
    }

    @Override
    public String getString(final String name) {
        return this.resource.getString(name);
    }

    @Override
    public String getString(final String name, final String defaultValue) {
        return this.resource.getString(name, defaultValue);
    }

    @Override
    public int getInt(final String name) {
        return this.resource.getInt(name);
    }

    @Override
    public int getInt(final String name, final int defaultValue) {
        return this.resource.getInt(name, defaultValue);
    }

    @Override
    public boolean getBoolean(final String name) {
        return this.resource.getBoolean(name);
    }

    @Override
    public boolean getBoolean(final String name, final boolean defaultValue) {
        return this.resource.getBoolean(name, defaultValue);
    }

    @Override
    public boolean isNull(final String name) {
        return this.resource.isNull(name);
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
    public void putAll(final Map<? extends String, ? extends JsonValue> map) {
        this.resource.putAll(map);
    }

    @Override
    public void clear() {
        this.resource.clear();
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

    @Override
    public String toString() {
        return this.resource.toString();
    }
}