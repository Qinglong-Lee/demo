package com.example.study.rmi.Hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RegistryTest {

    public RegistryTest() {}

    public static void main(String args[]) {

        try {
//            开启一个注册器，监听 1099 端口
            Registry registry = LocateRegistry.createRegistry(1099);

            System.err.println("Registry ready");
//            阻塞主进程，以持续监听
            Scanner scanner = new Scanner(System.in);
            scanner.next();
        } catch (Exception e) {
            System.err.println("Registry exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


