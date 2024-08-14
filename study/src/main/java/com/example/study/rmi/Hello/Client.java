package com.example.study.rmi.Hello;

import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        try {
//            直接通过【JNDI】的【InitialContext】代替 RMI 去和【registry】交互，因为 RMI 实现了 JNDI 的标准接口
            Hello hello = (Hello) new InitialContext().lookup("rmi://localhost:1099/Hello");
            System.out.println(hello.sayHello());
//            首先和【registry】建立连接
//            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
//            从【registry】查找某个【远程对象】，获得一个【stub】
//            客户端拥有要调用对象的【接口或者抽象类】，但是没有远程对象，因此需要从服务端调用
//            Hello stub = (Hello) registry.lookup("Hello");
//            通过【stub】调用远程对象，stub是一个代理类，代理了【接口】，封装了远程通信的细节
//            String response = stub.sayHello();
//            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


