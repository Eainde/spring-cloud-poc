package com.eainde.customer.controller;

import com.eainde.customer.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RibbonClient(name="custribbon")
public class CustomerController {
    private final RestTemplate restTemplate;
    private final String userUrl;

    CustomerController(final RestTemplate restTemplate,
                       @Value("${user.uri}") final String userUrl){
        this.restTemplate=restTemplate;
        this.userUrl=userUrl;
    }

    @GetMapping(value="/user", produces = "application/json")
    public ResponseEntity<List<UserDto>> findAll(){
       UserDto[] users=restTemplate.getForObject("http://custribbon/user/",UserDto[].class);
        return new ResponseEntity<>(Arrays.asList(users), HttpStatus.OK);
    }

}
