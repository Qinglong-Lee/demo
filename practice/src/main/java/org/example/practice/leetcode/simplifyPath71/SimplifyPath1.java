package org.example.practice.leetcode.simplifyPath71;

import java.util.Stack;

/**
 * 格式化 Unix 路径
 * 在 Unix 风格的文件系统中规则如下：
 *     一个点 '.' 表示当前目录本身。
 *     此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
 *     任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
 *     任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
 * 返回的 简化路径 必须遵循下述格式：
 *     始终以斜杠 '/' 开头。
 *     两个目录名之间必须只有一个斜杠 '/' 。
 *     最后一个目录名（如果存在）不能 以 '/' 结尾。
 *     此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 *
 * 思路：【将每个字符逐个压栈，分别判断每个字符入栈后对之前字符的影响】
 */
public class SimplifyPath1 {
    public String simplifyPath(String path) {
        Stack<Character> stack = new Stack<>();
        StringBuilder simplified = new StringBuilder();
        int dotCnt = 0;

        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);

            if (!stack.empty()) {
                Character last = stack.peek();
                if (last == '.') {
                    if (c == '.') {
                        stack.push(c);
                        if (dotCnt > 0) dotCnt++;
                    } else if (c == '/') {
                        if (dotCnt == 1) {
                            stack.pop();
                        } else if (dotCnt == 2) {
                            for (int j = 0; j < 3; j++) {
                                if (stack.size() > 1) stack.pop();
                            }
                            while (!stack.empty() && stack.peek() != '/') {
                                stack.pop();
                            }
                        } else {
                            stack.push(c);
                        }

                        dotCnt = 0;
                    } else {
                        stack.push(c);
                        dotCnt = 0;
                    }
                } else if (last == '/') {
                    if (c != '/') {
                        stack.push(c);
                        if (c == '.') dotCnt++;
                    }
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
                if (c == '.') dotCnt++;
            }
        }

        if (stack.size() > 1 && stack.peek() == '.') {
            if (dotCnt == 1) {
                stack.pop();
            }
            if (dotCnt == 2) {
                for (int j = 0; j < 3; j++) {
                    if (stack.size() > 1) stack.pop();
                }
                while (!stack.empty() && stack.peek() != '/') {
                    stack.pop();
                }
            }
        }

        if (stack.size() > 1 && stack.peek() == '/') {
            stack.pop();
        }

        while (!stack.empty()) {
            simplified.insert(0, stack.pop().toString());
        }

        return simplified.toString();
    }

    public static void main(String[] args) {
//        String path = "/home/";
//        String path = "/../";
//        String path = "/a//b////c/d//././/..";
        String path = "/hello../world";

        SimplifyPath1 test = new SimplifyPath1();
        System.out.println(test.simplifyPath(path));
    }
}
