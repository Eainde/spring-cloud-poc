package com.eainde.customer.config;

import com.eainde.customer.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("eainde-user-service")
public interface CustFeign {

    @RequestMapping(value="/user/")
    UserDto[] getUsers();
}
