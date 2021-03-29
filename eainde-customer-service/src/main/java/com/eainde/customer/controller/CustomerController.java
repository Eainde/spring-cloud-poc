package com.eainde.customer.controller;

import com.eainde.customer.dto.CustomerDto;
import com.eainde.customer.dto.OrderDto;
import com.eainde.customer.dto.UserDto;
import com.eainde.customer.service.CustHystrixService;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/customer")
/** I have commented RibbonClient & rest template out because we don't need it now as we are getting information from eureka server
 * This is useful when we are not using eureka and using ribbon standalone
 * We will use ribbon with eureka
 @RibbonClient(name="custribbon")
 **/
public class CustomerController {
    private final CustHystrixService service;
   /* private final RestTemplate restTemplate;
    private final String userUrl;
    // using this Eureka client we can information of the instance
    private final EurekaClient client;*/

    CustomerController(final CustHystrixService service){
        this.service=service;
    }

    //@HystrixCommand(fallbackMethod = "findAllFallback")
    @GetMapping(value="/user", produces = "application/json")
    public ResponseEntity<List<UserDto>> findAll() throws ExecutionException, InterruptedException {
        //String uri=client.getApplication("eainde-user-service").getInstances().get(0).getHomePageUrl();
       return new ResponseEntity<>(service.obtainUser().get(), HttpStatus.OK);
    }


    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<CustomerDto> findUserOrders() throws ExecutionException, InterruptedException {
        //String uri=client.getApplication("eainde-user-service").getInstances().get(0).getHomePageUrl();
        // Async call is happening here. It will not wait users service to complete to call order service.
        Future<List<UserDto>> users=service.obtainUser();
        Future<List<OrderDto>> orders=service.obtainOrder();
        CustomerDto customer=new CustomerDto();
        // It will wait when get() is called to get the result. It will not proceed further until get is completed.
        customer.setOrder(orders.get());
        customer.setUser(users.get());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
