package com.example.study.dataStructure;

import java.util.*;

public class TestSet {
    public static void main(String[] args) {
        Set<Integer> hashset = new HashSet<>();
        hashset.add(1);
        hashset.remove(1);
        hashset.iterator().next();

        LinkedHashSet<Integer> linkedHashSet  = new LinkedHashSet<>();
        linkedHashSet.add(1);
        linkedHashSet.remove(1);

        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        treeSet.add(1);
        treeSet.remove(1);
        treeSet.first();

    }
}
