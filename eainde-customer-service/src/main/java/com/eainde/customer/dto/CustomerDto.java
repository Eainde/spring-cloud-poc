package com.eainde.customer.dto;

import java.util.List;

public class CustomerDto {
  private List<UserDto> user;
  private List<OrderDto> order;

  public List<UserDto> getUser() {
    return user;
  }

  public void setUser(List<UserDto> user) {
    this.user = user;
  }

  public List<OrderDto> getOrder() {
    return order;
  }

  public void setOrder(List<OrderDto> order) {
    this.order = order;
  }
}
