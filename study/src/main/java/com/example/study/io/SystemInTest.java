package com.example.study.io;

import java.io.Console;

public class SystemInTest {
    public static void main(String[] args) {
        System.out.println("Please input...");
//        控制台输入字节流转化为 BufferedReader
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        控制台输入字节流转化为 Scanner
//        JDK 5.0 支持
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
//        }

//        利用 Console 实例获取输入
//        JDK 6.0 支持
//        如果 JVM 是在交互式命令行（比如 Windows 的 cmd）中启动的，并且输入输出没有重定向到另外的地方
//        那么就可以得到一个可用的 Console实例
//        在 IDE 运行的 Console 为 null，只支持命令行模式
//        运行 SystemInTest.class：D:\WORK\codes\demo\target\classes> java com.example.demo.io.SystemInTest
        Console console = System.console();
        if(console == null) {
            System.out.println("请在 cmd 启动！");
        }
        else {
            String line;
            while ((line = console.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
