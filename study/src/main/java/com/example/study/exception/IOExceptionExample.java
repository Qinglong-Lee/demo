package com.example.study.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOExceptionExample {
    public static void main(String[] args) {
        //IOException属于检查型异常
        //异味者如果几不用try-catch捕获异常，又不在方法头抛出异常，则编译不通过
        try {
            // 尝试打开一个不存在的文件
            BufferedReader reader = new BufferedReader(new FileReader("nonexistent_file.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            // 处理IOException异常
            System.out.println("发生IOException异常: " + e.getMessage());
            // 可以根据具体情况采取相应的解决方案
            // 这里的示例解决方案是打印异常堆栈跟踪
            e.printStackTrace();
        }
    }
}

