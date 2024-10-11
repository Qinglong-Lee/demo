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
 * 思路：【先切割，再对每个切割单元做入栈出栈操作】
 *
 * 由于路径最终一定是以【/】划分的，因此完全无需处理【//】或【///】
 * 直接将其看做多个【空目录】即可，即用【/】切割路径
 * 切割后得到：1.常规目录名，2【..】，3.【.】,4.【空】
 * 只有【..】需要【回退处理】,【.】和【空】直接忽略，常规目录入栈
 */
public class SimplifyPath2 {
    public String simplifyPath(String path) {
        StringBuilder simplified = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] arr = path.split("/");

        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (!s.isEmpty() && !s.equals(".")) {
                if (s.equals("..")) {
                     if(!stack.isEmpty()) stack.pop();
                } else {
                    stack.push(s);
                }
            }
        }

        while (!stack.isEmpty()) {
            simplified.insert(0,  stack.pop()).insert(0,  "/");
        }

        return simplified.isEmpty() ? "/" : simplified.toString();
    }

    public static void main(String[] args) {
//        String path = "/home/";
        String path = "/../";
//        String path = "/a//b////c/d//././/..";
//        String path = "/hello../world";

        SimplifyPath2 test = new SimplifyPath2();
        System.out.println(test.simplifyPath(path));
    }
}
