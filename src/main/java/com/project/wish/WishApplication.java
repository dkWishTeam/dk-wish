package com.project.wish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan(basePackages = {"com.project.wish"})
public class WishApplication {

    public static void main(String[] args) {
        //System.out.println("김주영1");
        //System.out.println("김태현");
        SpringApplication.run(WishApplication.class, args);
    }

}
