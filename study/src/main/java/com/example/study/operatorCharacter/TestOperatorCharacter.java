package com.example.study.operatorCharacter;

import java.math.BigInteger;

public class TestOperatorCharacter {
    public static void main(String[] args) {
        String binP = "01111111111111111111111111111111";
        System.out.println(Integer.parseInt(binP, 2));
        System.out.println(Integer.toBinaryString(Integer.parseInt(binP, 2)));

        String binN = "-1111111111111111111111111111111";
        System.out.println(Integer.parseInt(binN, 2));
        System.out.println(Integer.toBinaryString(Integer.parseInt(binN, 2)));
//        System.out.println(Integer.parseInt("1111111111111111111111111111111", 2));

        System.out.println(new BigInteger("11111111111111111111111111111111", 2));
        System.out.println(new BigInteger("11111111111111111111111111111111", 2).intValue());
        System.out.println(new BigInteger("11111111111111111111111111111111", 2).longValue());
//        System.out.println(Integer.toBinaryString(127));
//        System.out.println(Integer.toBinaryString(10 >> 1));
//        System.out.println(Integer.toBinaryString(-10));
//        System.out.println(Integer.parseInt(Integer.toBinaryString(10), 2));
//        System.out.println(Integer.parseInt(Integer.toBinaryString(-10), 2));
//        System.out.println(new BigInteger(Integer.toBinaryString(-10), 2).intValue());

//        System.out.println(Integer.toBinaryString(-10 >> 1));
//        System.out.println(new BigInteger(Integer.toBinaryString(-10 >> 1), 2).intValue());

//        System.out.println(-10 >> 1);
//        System.out.println(Integer.toBinaryString((-10) >>> 1));

//        System.out.println(new BigInteger(Integer.toBinaryString(-10 >>> 1), 2).intValue());
//        System.out.println(-10 >>> 1);
//        System.out.println(10 >>> 1);
    }
}
