package ru.mai.autocomplete;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListIntersectorBinarySearchTest {
    @Test
    public void testGetIntersection() throws Exception {
        {
            List<Integer> first = new ArrayList<>();
            first.add(0);
            first.add(1);
            first.add(2);

            List<Integer> second = new ArrayList<>();
            second.add(0);
            second.add(1);
            second.add(2);

            List<Integer> result = new ArrayList<>();
            result.add(0);
            result.add(1);
            result.add(2);

            ListIntersector<Integer> intersector = new ListIntersectorBinarySearch<>();
            intersector.addList(first);
            intersector.addList(second);

            Assert.assertEquals(result, intersector.getIntersection(3));
        }

        {
            List<Integer> first = new ArrayList<>();
            first.add(0);
            first.add(1);
            first.add(2);

            List<Integer> second = new ArrayList<>();
            second.add(0);
            second.add(1);
            second.add(2);

            List<Integer> result = new ArrayList<>();
            result.add(0);
            result.add(1);

            ListIntersector<Integer> intersector = new ListIntersectorBinarySearch<>();
            intersector.addList(first);
            intersector.addList(second);

            Assert.assertEquals(result, intersector.getIntersection(2));
        }

        {
            List<Integer> first = new ArrayList<>();
            first.add(1);
            first.add(2);

            List<Integer> second = new ArrayList<>();
            second.add(0);
            second.add(1);

            List<Integer> result = new ArrayList<>();
            result.add(1);

            ListIntersector<Integer> intersector = new ListIntersectorBinarySearch<>();
            intersector.addList(first);
            intersector.addList(second);

            Assert.assertEquals(result, intersector.getIntersection(2));
        }

        {
            List<Integer> first = new ArrayList<>();
            first.add(0);
            first.add(1);
            first.add(2);
            first.add(10);

            List<Integer> second = new ArrayList<>();
            second.add(1);
            second.add(2);
            second.add(3);
            second.add(10);

            List<Integer> third = new ArrayList<>();
            third.add(2);
            third.add(3);
            third.add(4);
            third.add(10);

            List<Integer> result = new ArrayList<>();
            result.add(2);
            result.add(10);

            ListIntersector<Integer> intersector = new ListIntersectorBinarySearch<>();
            intersector.addList(first);
            intersector.addList(second);
            intersector.addList(third);

            Assert.assertEquals(result, intersector.getIntersection(10));
        }
    }
}
