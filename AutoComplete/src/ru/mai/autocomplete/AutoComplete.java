package ru.mai.autocomplete;

import java.util.List;

public interface AutoComplete<T> {
    public List<Result<T>> getObjects(String query);

    public void addObject(String query, T object);
}
