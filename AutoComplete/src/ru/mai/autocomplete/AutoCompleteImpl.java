package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteImpl<T> implements AutoComplete<T> {
    private List<T> objects;
    private FuzzySearcher<Integer> reverseIndex;

    public AutoCompleteImpl() {
        objects = new ArrayList<>();
        reverseIndex = new FuzzySearcherTrieLevenstein<>();
    }

    @Override
    public List<T> getObjects(String query) {
        List<T> res = new ArrayList<>();
        return res;
    }

    @Override
    public void addObject(String query, T object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
