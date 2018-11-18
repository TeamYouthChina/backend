package com.youthchina;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/config.properties")

public class YouthchinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouthchinaApplication.class, args);
    }

}
