package org.example.practice.leetcode.validBrace20;

import java.util.*;

/**
 * 校验括号是否闭合
 *
 * 思路：【栈】
 *
 * 利用双向队列模拟一个栈，之所以用双向队列，是因为单向队列是【先进先出】，而栈需要【先进后出】
 * 将所有括号依次入栈，入栈前先获取栈顶元素，和当前元素对比，看是否匹配括号
 * 匹配则出栈，且当前元素不入栈。否则当前元素入栈
 * 最后判断栈是否为空
 */
public class ValidBrace1 {
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char[] arr = s.toCharArray();

        for (char c :
                arr) {
            Character last = deque.peekLast();
            if (last != null && isPair(last, c)) {
                deque.pollLast();
            } else {
                deque.offer(c);
            }
        }

        return deque.size() == 0;
    }

    private boolean isPair(char c1, char c2) {
        if (c1 == '{' && c2 == '}'
                || c1 == '[' && c2 == ']'
                || c1 == '(' && c2 == ')') {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
//        String s = "()";
        String s = ")(";

        ValidBrace1 test = new ValidBrace1();
        System.out.println(test.isValid(s));
    }
}
