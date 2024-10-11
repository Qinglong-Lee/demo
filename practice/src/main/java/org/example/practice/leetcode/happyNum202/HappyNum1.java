package org.example.practice.leetcode.happyNum202;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断正整数是否为【快乐数】，即循环求每位的平方和，将结果用作下次循环计算，直到结果为 1 或无限循环
 *
 * 思路：【利用 set 判断无限循环】
 * 本题的重点不是如何简化计算，而是【如何快速而简单地判断是否无限循环】
 *
 * 用一个 set 记录所有出现的结果，每次计算出新结果都判断是否已出现，由此判断是否出现无限循环
 * 将整数转为字符串然后获取 charArray，再将每个 char 转换为 int，由此获取整数的每位数字
 */
public class HappyNum1 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>() {{
            add(n);
        }};
        char[] chars;
        int next = n;

        while (next != 1) {
            chars = String.valueOf(next).toCharArray();
            next = 0;

            for (int i = 0; i < chars.length; i++) {
                next += charToInt(chars[i]) * charToInt(chars[i]);
            }

            if (set.contains(next)) return false;

            set.add(next);
        }

        return true;
    }

    private int charToInt(char c) {
        return c - '0';
    }

    public static void main(String[] args) {
//        int n = 19;
        int n = 1;

        HappyNum1 test = new HappyNum1();
        System.out.println(test.isHappy(n));
    }
}
