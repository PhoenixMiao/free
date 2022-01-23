package com.phoenix.free;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.phoenix.free.mapper")
@ComponentScan(basePackages = {"com.phoenix"})
public class FreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeApplication.class, args);
    }

}
