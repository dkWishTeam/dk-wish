package com.project.wish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class WishApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishApplication.class, args);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }

}
