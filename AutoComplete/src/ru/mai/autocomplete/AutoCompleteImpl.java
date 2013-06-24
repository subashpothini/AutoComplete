package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteImpl<T> implements AutoComplete<T> {
    private final List<String> texts;
    private final List<T> objects;
    private final FuzzySearcher<Integer> reverseIndex;
    private final StringSpliter spliter;

    public AutoCompleteImpl() {
        texts = new ArrayList<>();
        objects = new ArrayList<>();
        reverseIndex = new FuzzySearcherTrieLevenstein<>();

        final String delimiters = " ,.:;!'\"";
        spliter = new StringSpliterImpl(delimiters);
    }

    @Override
    public List<Result<T>> getObjects(String query) {
        ListIntersector<Integer> intersector = new ListIntersectorBinarySearch<>();
        List<String> tokens = spliter.split(query);

        for (int i = 0; i < tokens.size(); i++) {
            boolean last = ((i + 1) == tokens.size());
            String token = tokens.get(i);
            List<Integer> listToAdd = (last ? reverseIndex.getObjectsByPrefix(token, 0) : reverseIndex.getObjects(token, 0));
            intersector.addList(listToAdd);
        }

        List<Integer> intersection = intersector.getIntersection(Integer.MAX_VALUE);
        List<Result<T>> result = new ArrayList<>(intersection.size());

        for (Integer id : intersection)
            result.add(new Result<>(texts.get(id), objects.get(id)));

        return result;
    }

    @Override
    public void addObject(String query, T object) {
        List<String> tokens = spliter.split(query);
        Integer id = objects.size();

        for (String token : tokens)
            reverseIndex.addObject(token, id);

        texts.add(query);
        objects.add(object);
    }
}
