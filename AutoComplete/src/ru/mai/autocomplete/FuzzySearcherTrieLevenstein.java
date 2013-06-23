package ru.mai.autocomplete;

import java.util.Set;
import java.util.TreeSet;

public class FuzzySearcherTrieLevenstein<T> implements FuzzySearcher<T> {
    private final FactoryTrieNode<T> nodeFactory;
    private final TrieNode<T> root;

    public FuzzySearcherTrieLevenstein() {
        nodeFactory = new FactoryTrieNode<>();
        root = nodeFactory.produce();
    }

    private TrieNode<T> getRoot() {
        return root;
    }

    @Override
    public void addObject(CharSequence word, T object) {
        TrieNode<T> cursor = root;

        for (int posInWord = 0; posInWord < word.length(); posInWord++) {
            // Add object to set
            cursor.getEndsBelow().add(object);

            // Move cursor
            char c = word.charAt(posInWord);
            TrieNode<T> cursorNext = cursor.getNext(c);

            if (cursorNext == null)
                cursor.putNext(c, cursorNext = nodeFactory.produce());

            cursor = cursorNext;
        }

        cursor.getEndsHere().add(object);
    }

    private Set<TrieNode<T>> getNodes(CharSequence word, int mistakes) {
        return null;
    }

    @Override
    public Set<T> getObjects(CharSequence word, int mistakes) {
        Set<TrieNode<T>> nodes = getNodes(word, mistakes);
        Set<T> result = new TreeSet<>();

        for (TrieNode<T> node : nodes)
            result.addAll(node.getEndsHere());

        return result;
    }

    @Override
    public Set<T> getObjectsByPrefix(CharSequence prefix, int mistakes) {
        Set<TrieNode<T>> nodes = getNodes(prefix, mistakes);
        Set<T> result = new TreeSet<>();

        for (TrieNode<T> node : nodes) {
            result.addAll(node.getEndsHere());
            result.addAll(node.getEndsBelow());
        }

        return result;
    }
}
