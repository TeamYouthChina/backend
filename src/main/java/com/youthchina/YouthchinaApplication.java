package com.youthchina;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
@PropertySource("classpath:/config.properties")
@MapperScan("com.youthchina.dao")
public class YouthchinaApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(YouthchinaApplication.class, args);
    }



}
