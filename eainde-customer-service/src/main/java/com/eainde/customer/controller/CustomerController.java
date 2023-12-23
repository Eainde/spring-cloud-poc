package com.eainde.customer.controller;

import com.eainde.customer.dto.CustomerDto;
import com.eainde.customer.dto.OrderDto;
import com.eainde.customer.dto.UserDto;
import com.eainde.customer.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    CustomerController(final CustomerService service) {
        this.service = service;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<List<UserDto>> findAll() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(service.obtainUser(), HttpStatus.OK);
    }


    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomerDto> findUserOrders() throws ExecutionException, InterruptedException {
        List<UserDto> users = service.obtainUser();
        List<OrderDto> orders = service.obtainOrder();
        CustomerDto customer = new CustomerDto();
        customer.setOrder(orders);
        customer.setUser(users);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
