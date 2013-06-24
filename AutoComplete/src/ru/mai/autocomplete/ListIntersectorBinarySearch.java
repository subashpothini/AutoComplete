package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListIntersectorBinarySearch<V extends Comparable<V>> implements ListIntersector<V> {
    private final List<List<V>> lists;

    public ListIntersectorBinarySearch() {
        lists = new ArrayList<>();
    }

    public void addList(List<V> list) {
        lists.add(list);
    }

    public List<V> getIntersection(int howMuch) {
        List<V> result = new ArrayList<>();

        final int n = lists.size();
        int[] currentPositions = new int[n];

        while (result.size() < howMuch && !isEnd(currentPositions)) {
            V maxValue = null;

            for (int i = 0; i < n; i++) {
                V value = lists.get(i).get(currentPositions[i]);
                if (maxValue == null || value.compareTo(maxValue) > 0)
                    maxValue = value;
            }

            boolean fail = false;

            for (int i = 0; i < n; i++) {
                int index = Collections.binarySearch(lists.get(i), maxValue), newPos;

                if (index < 0) {
                    fail = true;
                    newPos = ~index;
                } else {
                    newPos = index + 1;
                }

                currentPositions[i] = newPos;
            }

            if (!fail)
                result.add(maxValue);
        }

        return result;
    }

    private boolean isEnd(int[] currentPositions) {
        for (int i = 0; i < currentPositions.length; i++)
            if (currentPositions[i] >= lists.get(i).size())
                return true;
        return false;
    }
}
