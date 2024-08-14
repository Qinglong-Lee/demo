package com.example.study.jndi;

import com.example.study.rmi.Hello.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceLookupTest {
    @Autowired
    public ResourceLookup resourceLookup;

    @Test
    public void testResouceLookup() throws RemoteException {
        Hello hello = resourceLookup.hello;
        System.out.println(hello.sayHello());
    }
}