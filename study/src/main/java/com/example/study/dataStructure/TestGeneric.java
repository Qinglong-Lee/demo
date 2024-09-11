package com.example.study.dataStructure;

import cn.hutool.core.annotation.scanner.AbstractTypeAnnotationScanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1, 1);
        map.put("2", "2");
        map.put(new TestGeneric(), new Object());
        System.out.println(map.toString());

        List list = new ArrayList<>();
        list.add("1");
        list.add(2);
        list.add(new Object());
        System.out.println(list.toString());

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        String str = list2.get(0);
    }
}
