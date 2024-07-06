package com.example.study.varTrans;

public class TestJavaVarTrans {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(1);
        transfer(a);
        System.out.println(a);
    }

    public static void transfer(Integer i) {

        i = Integer.valueOf(2);
    }
}
