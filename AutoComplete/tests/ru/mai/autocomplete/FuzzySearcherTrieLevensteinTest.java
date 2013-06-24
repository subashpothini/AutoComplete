package ru.mai.autocomplete;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FuzzySearcherTrieLevensteinTest {
    @Test
    public void testGetObjects() throws Exception {
        {
            FuzzySearcher<Integer> searcher = new FuzzySearcherTrieLevenstein<>();
            searcher.addObject("abc", 0);
            searcher.addObject("zzz", 1);
            searcher.addObject("qwe", 2);
            searcher.addObject("qwe", 3);

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(0);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("abc", 0));
            }

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(1);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("zzz", 0));
            }

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(2);
                result.add(3);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("qwe", 0));
            }
        }
    }

    @Test
    public void testGetObjectsByPrefix() throws Exception {
        {
            FuzzySearcher<Integer> searcher = new FuzzySearcherTrieLevenstein<>();
            searcher.addObject("abc", 0);
            searcher.addObject("zzz", 1);
            searcher.addObject("qwe", 2);
            searcher.addObject("qwe", 3);
            searcher.addObject("zqwz", 4);

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(0);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("ab", 0));
            }

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(1);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("zzz", 0));
            }

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(2);
                result.add(3);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("q", 0));
            }

            {
                List<Integer> result = new ArrayList<Integer>();
                result.add(1);
                result.add(4);
                Assert.assertEquals(result, searcher.getObjectsByPrefix("z", 0));
            }
        }
    }
}
