package com.example.study.thread.practice;

/**
 * 两个线程轮流输出
 * 一个线程输出 1-52 的数字，另一个输出 A-Z 的字符
 * 数字输出线程每次输出两个数字，字符输出线程每次输出一个字符
 */
public class TakeTurns {
    public String turn = "N";

    public synchronized void soufNumber() {
        for (int i = 1; i <= 52; i++) {
            while ("C".equals(turn)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() +" Number: " + i);

            if (i % 2 == 0) turn = "C";
            notify();

        }
    }

    public synchronized void soufCharacter() {
        for (char i = 'A'; i <= 'Z'; i++) {
            if ("N".equals(turn)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + " Character: " + i);
            turn = "N";
            notify();
        }
    }

    public static void main(String[] args) {
        TakeTurns test = new TakeTurns();
        new Thread(test::soufNumber, "N1").start();
        new Thread(test::soufNumber, "N2").start();
        new Thread(test::soufCharacter, "C1").start();
        new Thread(test::soufCharacter, "C2").start();
    }
}
