package org.example.practice.leetcode.validBrace20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 校验括号是否闭合
 *
 * 思路：【栈 + Map】
 *
 * Java 本身提供 Stack 类作为栈的基础实现
 * 无需将所有括号都入栈，只需入栈【左括号】
 * 如果是【右括号】，则出栈栈顶括号并对比，不匹配则直接返回 false，无需后序判断，提前结束
 * 利用 HashMap 来匹配左右括号
 */
public class ValidBrace2 {
    Map<Character, Character> pairs = new HashMap<>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()";
//        String s = ")(";

        ValidBrace2 test = new ValidBrace2();
        System.out.println(test.isValid(s));
    }
}
