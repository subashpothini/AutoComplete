package ru.mai.autocomplete;

import org.junit.Assert;
import org.junit.Test;

public class TrieNodeTest {
    @Test
    public void testFactory() throws Exception {
        FactoryTrieNode<Integer> factory = new FactoryTrieNode<>();

        // Check that two TrieNodes from same factory has different id's.
        TrieNode<Integer> first = factory.produce(), second = factory.produce();
        Assert.assertTrue(first.getId() != second.getId());
    }
}
