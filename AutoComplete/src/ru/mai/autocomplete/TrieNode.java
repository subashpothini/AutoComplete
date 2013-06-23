package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrieNode<K> implements Comparable<TrieNode<K>> {
    private final int id;
    private final Map<Character, TrieNode<K>> next;
    private final List<K> endsHere, endsBelow;

    public TrieNode(int id) {
        this.id = id;

        next = new TreeMap<>();
        endsHere = new ArrayList<>();
        endsBelow = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public TrieNode<K> getNext(Character c) {
        return next.get(c);
    }

    public void putNext(Character c, TrieNode<K> node) {
        next.put(c, node);
    }

    public List<K> getEndsHere() {
        return endsHere;
    }

    public List<K> getEndsBelow() {
        return endsBelow;
    }

    @Override
    public int compareTo(TrieNode<K> o) {
        return Integer.compare(getId(), o.getId());
    }
}
