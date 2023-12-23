package com.eainde.customer.service;

import com.eainde.customer.config.UserFeign;
import com.eainde.customer.dto.OrderDto;
import com.eainde.customer.dto.UserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    private final RestTemplate restTemplate;
    private final UserFeign client;

    CustomerService(final RestTemplate restTemplate, final UserFeign client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    public List<UserDto> obtainUser() {

        UserDto[] users = client.getUsers();
        return Arrays.asList(users);
    }

    public List<OrderDto> obtainOrder() {
        ResponseEntity<OrderDto[]> responseEntity =
                restTemplate.getForEntity("http://eainde-order-service/order/", OrderDto[].class);

        return List.of(responseEntity.getBody());
    }

    public List<UserDto> findAllFallback() {
        return new ArrayList<>();
    }
}
