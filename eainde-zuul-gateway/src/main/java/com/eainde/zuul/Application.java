package com.eainde.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackages = {"com.eainde.zuul"})
@EnableDiscoveryClient
@EnableZuulProxy
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
