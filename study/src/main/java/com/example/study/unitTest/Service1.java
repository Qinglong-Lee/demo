package com.example.study.unitTest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Service1 {
    @Autowired
    Service2 service2;

    public String callService2(String msg) throws RuntimeException{
        return service2.sout(msg);
    }
}
