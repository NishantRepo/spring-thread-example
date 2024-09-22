package com.nishant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringThreadExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringThreadExampleApplication.class, args);
    }

}
