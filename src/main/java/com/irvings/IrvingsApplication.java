package com.irvings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrvingsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrvingsApplication.class, args);
        System.out.println("=======================================");
        System.out.println("Irving's Order App API 启动成功！");
        System.out.println("API 地址: http://localhost:8080/h2-console");
        System.out.println("=======================================");
    }
}
