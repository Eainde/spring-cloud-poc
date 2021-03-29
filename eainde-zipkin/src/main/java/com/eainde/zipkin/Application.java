package com.eainde.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication(scanBasePackages = {"com.eainde.zipkin"})
@EnableZipkinServer
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
