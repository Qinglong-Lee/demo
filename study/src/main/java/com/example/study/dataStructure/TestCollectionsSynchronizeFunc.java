package com.example.study.dataStructure;

import cn.hutool.core.lang.hash.Hash;

import java.util.Collections;
import java.util.HashMap;

public class TestCollectionsSynchronizeFunc {
    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        Collections.synchronizedMap(map);
    }
}
