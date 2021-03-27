package com.eainde.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.eainde.order"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
