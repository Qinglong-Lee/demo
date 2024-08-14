package com.example.study.rmi.Hello;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class Server {

    public Server() {}


    public static void main(String args[]) {

        try {
//            如果远程对象继承【UnicastRemoteObject】，则可以在构造的时候传递暴露端口
            RemoteObj obj = new RemoteObj(1098);
//            将【远程对象的 skeleton】暴露到【1098】端口，若不指定或指定为【0】，则 RMI 会随机选择一个可用端口
//            也可以直接让 RemoteObj 继承【UnicastRemoteObject】，这样就无需通过【exportObject】暴露远程对象，而是可以直接绑定了
//            Hello skeleton = (Hello) UnicastRemoteObject.exportObject(obj, 1098);

//            通过传递一个 Hashtable 初始化 JNDI 上下文
//            Hashtable<String, String> env = new Hashtable<>();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContext");
//            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
//            new InitialContext(env).bind("Hello", obj);

//            通过设置系统属性配置 JNDI 控制的注册器，之后便可通过 JNDI 代替 RMI 完成和注册器之间的交互
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
//            new InitialContext().bind("Hello", skeleton);
            new InitialContext().bind("Hello", obj);
//            new InitialContext().bind("Hello", new RemoteObj());

//             如果不通过系统属性指定 JNDI 的实现服务，就需要再绑定远程对象的时候指定
//            new InitialContext().bind("rmi://localhost:1099/Hello", skeleton);

            // Bind the remote object's stub in the registry
//            将【远程对象或 skeleton】绑定到注册器
//            【registry.bind】用于【服务器和注册器在一个进程】场景，因为已经知道了注册器，所以【name】无需指定注册器信息
//            Registry registry = LocateRegistry.createRegistry(1099);
//            registry.bind("Hello", skeleton);
//            【Naming.bind】用于【注册器单独部署】场景，因为当前进程不知道注册器信息，因此需要再【name】中指定注册器信息【rmi://localhost:1099】
//            Naming.bind("rmi://localhost:1099/Hello", skeleton);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


