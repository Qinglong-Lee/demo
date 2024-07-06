package com.example.study.finalTest;

public class FinalTest {
    public final Object val = new Object();

    public static final String val2 = "TEST";

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        System.out.println(finalTest.val);
        FinalTest finalTest2 = new FinalTest();
        System.out.println(finalTest2.val);
        System.out.println(FinalTest.val2);
        //finalTest.val ="";
    }
}
