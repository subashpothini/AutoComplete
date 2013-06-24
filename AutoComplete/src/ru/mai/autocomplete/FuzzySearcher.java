package ru.mai.autocomplete;

import java.util.List;

public interface FuzzySearcher<T> {
    void addObject(CharSequence word, T object);

    List<T> getObjects(CharSequence word, int mistakes);

    List<T> getObjectsByPrefix(CharSequence prefix, int mistakes);
}
