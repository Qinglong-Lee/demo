package com.example.study.exception;

public class TryCatchFinallyTest {
    public static void main(String[] args) {
        try {
            int i = 1 / 0;
//        } catch (Exception e) { //不 catch 最终也会将异常抛出，而不是忽略
//            System.out.println(e.getMessage());
        } finally {
            System.out.println("done");
        }
    }
}
