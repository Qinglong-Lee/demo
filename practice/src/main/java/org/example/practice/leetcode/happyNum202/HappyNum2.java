package org.example.practice.leetcode.happyNum202;

/**
 * 判断正整数是否为【快乐数】，即循环求每位的平方和，将结果用作下次循环计算，直到结果为 1 或无限循环
 *
 * 思路：【利用快慢指针判断无限循环】
 * 本题的重点不是如何简化计算，而是【如何快速而简单地判断是否无限循环】
 *
 * 【用一个 set 记录所有出现的结果】的弊端是存储量可能会很大，空间复杂度高
 * 【循环问题】最经典的解决方式就是【快慢指针】：慢指针每次走一步，快指针两布，则只要有循环就一定会相遇
 * 注意：最终结果为 1，也会循环，所以最终要判断结果是否为 1
 *
 * 另外：获取正整数的每位数的经典解法是【对 10 取模】，效率比【int -> String -> charArray】高
 */
public class HappyNum2 {
    public boolean isHappy(int n) {
        int slow = n, fast = n;

        do {
            slow = multiplySum(slow);
            fast = multiplySum(multiplySum(fast));
        } while (slow != fast);

        return slow == 1;
    }

    private int multiplySum(int n) {
        int rst = 0;

        while (n > 0) {
            rst += (n%10) * (n%10);
            n = n/10;
        }

        return rst;
    }

    public static void main(String[] args) {
//        int n = 19;
//        int n = 1;
        int n = 10;

        HappyNum2 test = new HappyNum2();
        System.out.println(test.isHappy(n));
    }
}
