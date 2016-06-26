package com.thoughtworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
public class SpringApplicationConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationConfiguration.class, args);
    }

    /*@Bean
    public CacheManager cacheManager(){
        return new GuavaCacheManager("greetings");
    }*/
}
