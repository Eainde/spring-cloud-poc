package com.eainde.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.eainde.customer"})
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerApplication {
    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class, args);
    }
}
