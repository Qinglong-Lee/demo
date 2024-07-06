package com.example.study;

import java.util.Arrays;
import java.util.List;

public class StringSplitTest {
    public static void main(String[] args) {
        System.out.println(split(null, ","));
    }

    public static List<String> split(String str, String splitter) {
        if (str == null) {
            return null;
        }

        String[] addrArray = str.split(splitter);
        return Arrays.asList(addrArray);
    }
}
