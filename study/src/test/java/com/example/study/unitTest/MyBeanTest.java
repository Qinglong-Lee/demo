package com.example.study.unitTest;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)//Junit4
@SpringBootTest
public class MyBeanTest {
    @Autowired
    MyBean bean;

    @Test
    public void testSout() {
        bean.sout();
    }
}