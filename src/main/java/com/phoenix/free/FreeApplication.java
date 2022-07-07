package com.phoenix.free;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.phoenix.free"})
@EnableCaching
@EnableScheduling
@MapperScan("com.phoenix.free.mapper")
public class FreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeApplication.class, args);
    }

}
