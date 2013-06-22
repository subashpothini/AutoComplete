package ru.mai.autocomplete;

import java.util.List;

public interface ListIntersector<V extends Comparable<V>> {
    public void addList(List<V> list);

    public List<V> getIntersection(int howMuch);
}
