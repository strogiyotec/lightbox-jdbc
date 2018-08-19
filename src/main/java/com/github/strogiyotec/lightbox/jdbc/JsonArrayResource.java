package com.github.strogiyotec.lightbox.jdbc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.json.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

@AllArgsConstructor
@EqualsAndHashCode(of = "origin")
public abstract class JsonArrayResource implements JsonArray {

    private final JsonArray origin;

    public JsonArrayResource(final Supplier<JsonArray> supplier) {
        this(
                supplier.get()
        );
    }

    @Override
    public JsonObject getJsonObject(final int i) {
        return this.origin.getJsonObject(i);
    }

    @Override
    public JsonArray getJsonArray(final int i) {
        return this.origin.getJsonArray(i);
    }

    @Override
    public JsonNumber getJsonNumber(final int i) {
        return this.origin.getJsonNumber(i);
    }

    @Override
    public JsonString getJsonString(final int i) {
        return this.origin.getJsonString(i);
    }

    @Override
    public <T extends JsonValue> List<T> getValuesAs(final Class<T> aClass) {
        return this.origin.getValuesAs(aClass);
    }

    @Override
    public String getString(final int i) {
        return this.origin.getString(i);
    }

    @Override
    public String getString(final int i, final String s) {
        return this.origin.getString(i,s);
    }

    @Override
    public int getInt(final int i) {
        return this.origin.getInt(i);
    }

    @Override
    public int getInt(final int i, final int i1) {
        return this.origin.getInt(i,i1);
    }

    @Override
    public boolean getBoolean(final int i) {
        return this.origin.getBoolean(i);
    }

    @Override
    public boolean getBoolean(final int i, final boolean b) {
        return this.origin.getBoolean(i,b);
    }

    @Override
    public boolean isNull(final int i) {
        return this.origin.isNull(i);
    }

    @Override
    public int size() {
        return this.origin.size();
    }

    @Override
    public boolean isEmpty() {
        return this.origin.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.origin.contains(o);
    }

    @Override
    public Iterator<JsonValue> iterator() {
        return this.origin.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.origin.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return this.origin.toArray(a);
    }

    @Override
    public boolean add(final JsonValue jsonValue) {
        return this.origin.add(jsonValue);
    }

    @Override
    public boolean remove(final Object o) {
        return this.origin.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return this.origin.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends JsonValue> c) {
        return this.origin.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends JsonValue> c) {
        return this.origin.addAll(index,c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return this.origin.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return this.origin.removeAll(c);
    }

    @Override
    public void clear() {
        this.origin.clear();
    }

    @Override
    public JsonValue get(final int index) {
        return this.origin.get(index);
    }

    @Override
    public JsonValue set(final int index, final JsonValue element) {
        return this.origin.set(index,element);
    }

    @Override
    public void add(final int index, final JsonValue element) {
        this.origin.add(index,element);
    }

    @Override
    public JsonValue remove(final int index) {
        return this.origin.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return this.origin.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return this.origin.lastIndexOf(o);
    }

    @Override
    public ListIterator<JsonValue> listIterator() {
        return this.origin.listIterator();
    }

    @Override
    public ListIterator<JsonValue> listIterator(final int index) {
        return this.origin.listIterator(index);
    }

    @Override
    public List<JsonValue> subList(final int fromIndex, final int toIndex) {
        return this.origin.subList(fromIndex,toIndex);
    }

    @Override
    public ValueType getValueType() {
        return this.origin.getValueType();
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }
}
