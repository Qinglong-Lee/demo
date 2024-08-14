package com.example.study.rmi.Hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObj extends UnicastRemoteObject implements Hello{
    protected RemoteObj(int port) throws RemoteException {
        super(port);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }
}
