package ru.mai.autocomplete;

import java.util.List;

public interface AutoComplete<T> {
    public List<T> getObjects(String query);

    public void addObject(String query, T object);
}
