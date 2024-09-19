package com.example.study.common;

import java.util.Arrays;
import java.util.List;

public class ArrayListConvert {
    public static int[] listToArray(List<Integer> list) {
        return  list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<Integer> arrayToList(int[] array) {
        return Arrays.stream(array).boxed().toList();
    }
}
