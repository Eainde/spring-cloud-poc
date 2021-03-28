package com.eainde.customer.controller;

import com.eainde.customer.dto.UserDto;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer")
// I have commented RibbonClient & rest template out because we don't need it now as we are getting information from eureka server
// This is useful when we are not using eureka and using ribbon standalone
// We will use ribbon with eureka
//@RibbonClient(name="custribbon")
public class CustomerController {
    private final RestTemplate restTemplate;
    //private final String userUrl;
    // using this Eureka client we can information of the instance
    //private final EurekaClient client;

    CustomerController(final RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    @GetMapping(value="/user", produces = "application/json")
    public ResponseEntity<List<UserDto>> findAll(){
        //String uri=client.getApplication("eainde-user-service").getInstances().get(0).getHomePageUrl();
       UserDto[] users=restTemplate.getForObject("http://eainde-user-service"+"/user/",UserDto[].class);
        return new ResponseEntity<>(Arrays.asList(users), HttpStatus.OK);
    }

    public ResponseEntity<List<UserDto>> findAllFallback(){
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
