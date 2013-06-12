package ru.mai.autocomplete;


public class FactoryTrieNode<K> implements Factory<TrieNode<K>> {
    private int currentId;

    public FactoryTrieNode() {
        // hello world!
        currentId = 0;
    }

    @Override
    public TrieNode<K> produce() {
        return new TrieNode<>(currentId++);
    }
}
