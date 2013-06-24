package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class FuzzySearcherTrieLevenstein<T> implements FuzzySearcher<T> {
    private final FactoryTrieNode<T> nodeFactory;
    private final TrieNode<T> root;

    public FuzzySearcherTrieLevenstein() {
        nodeFactory = new FactoryTrieNode<>();
        root = nodeFactory.produce();
    }

    @Override
    public void addObject(CharSequence word, T object) {
        TrieNode<T> cursor = root;

        for (int posInWord = 0; posInWord < word.length(); posInWord++) {
            // Move cursor
            char c = word.charAt(posInWord);
            TrieNode<T> cursorNext = cursor.getNext(c);

            if (cursorNext == null)
                cursor.putNext(c, cursorNext = nodeFactory.produce());

            cursor = cursorNext;

            // Add object to set
            if (!cursor.getEndsBelow().contains(object))
                cursor.getEndsBelow().add(object);
        }

        if (!cursor.getEndsHere().contains(object))
            cursor.getEndsHere().add(object);
    }

    private List<TrieNode<T>> getNodes(CharSequence word, int mistakes) {
        TrieNode<T> cursor = root;

        for (int posInWord = 0; posInWord < word.length() && cursor != null; posInWord++) {
            // Move cursor
            char c = word.charAt(posInWord);
            cursor = cursor.getNext(c);
        }

        List<TrieNode<T>> result = new ArrayList<>();

        if (cursor != null)
            result.add(cursor);

        return result;
    }

    @Override
    public List<T> getObjects(CharSequence word, int mistakes) {
        List<TrieNode<T>> nodes = getNodes(word, mistakes);
        List<T> result = new ArrayList<>();

        for (TrieNode<T> node : nodes)
            result.addAll(node.getEndsHere());

        return result;
    }

    @Override
    public List<T> getObjectsByPrefix(CharSequence prefix, int mistakes) {
        List<TrieNode<T>> nodes = getNodes(prefix, mistakes);
        List<T> result = new ArrayList<>();

        for (TrieNode<T> node : nodes)
            result.addAll(node.getEndsBelow());

        return result;
    }
}
