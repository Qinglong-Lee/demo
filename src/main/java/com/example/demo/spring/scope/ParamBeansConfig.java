package com.example.demo.spring.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParamBeansConfig {
    @Bean
    public String param1() {
        return "PARAM1";
    }
    @Bean
    public String param2() {
        return "PARAM2";
    }
}
