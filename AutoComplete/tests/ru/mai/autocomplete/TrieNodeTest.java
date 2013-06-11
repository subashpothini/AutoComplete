package ru.mai.autocomplete;

import org.junit.Assert;
import org.junit.Test;

public class TrieNodeTest {
    @Test
    public void testF() throws Exception {
        TrieNode node = new TrieNode();

        Assert.assertEquals(node.f(), 1);
    }
}
