package ru.mai.autocomplete;

import java.util.Set;

public interface FuzzySearcher<T> {
    void addObject(CharSequence word, T object);

    Set<T> getObjects(CharSequence word, int mistakes);

    Set<T> getObjectsByPrefix(CharSequence prefix, int mistakes);
}
