package ru.mai.autocomplete;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrieNode<K> implements Comparable<TrieNode<K>> {
    private final int id;
    private final Map<Character, TrieNode<K>> next;
    private final Set<K> endsHere, endsBelow;

    public TrieNode(int id) {
        this.id = id;

        next = new TreeMap<>();
        endsHere = new TreeSet<>();
        endsBelow = new TreeSet<>();
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

    public Set<K> getEndsHere() {
        return endsHere;
    }

    public Set<K> getEndsBelow() {
        return endsBelow;
    }

    @Override
    public int compareTo(TrieNode<K> o) {
        return Integer.compare(getId(), o.getId());
    }
}
