package com.youthchina.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class RabbitmqConfig {
    @Bean
    public Queue mail(){
        return new Queue("email");
    }
}
