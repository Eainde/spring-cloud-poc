package com.eainde.customer.service;

import com.eainde.customer.config.CustFeign;
import com.eainde.customer.dto.OrderDto;
import com.eainde.customer.dto.UserDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CustHystrixService {
  private final RestTemplate restTemplate;
  private final CustFeign client;

  CustHystrixService(final RestTemplate restTemplate, final CustFeign client) {
    this.restTemplate = restTemplate;
    this.client = client;
  }

  @HystrixCommand(fallbackMethod = "findAllFallback")
  public Future<List<UserDto>> obtainUser() {
    return new AsyncResult<List<UserDto>>() {
      @Override
      public List<UserDto> invoke() {
        // UserDto[]
        // users=restTemplate.getForObject("http://eainde-user-service"+"/user/",UserDto[].class);
        UserDto[] users = client.getUsers();
        return Arrays.asList(users);
      }
    };
  }

  @HystrixCommand
  public Future<List<OrderDto>> obtainOrder() {
    return new AsyncResult<List<OrderDto>>() {
      @Override
      public List<OrderDto> invoke() {
        OrderDto[] orders =
            restTemplate.getForObject("http://eainde-order-service" + "/order/", OrderDto[].class);
        return Arrays.asList(orders);
      }
    };
  }

  public List<UserDto> findAllFallback() {
    return new ArrayList<>();
  }
}
