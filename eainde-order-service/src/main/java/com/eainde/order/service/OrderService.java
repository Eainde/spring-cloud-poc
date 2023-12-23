package com.eainde.order.service;

import com.eainde.order.entity.Order;
import com.eainde.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final OrderRepository repository;

  OrderService(final OrderRepository repository) {
    this.repository = repository;
  }

  public Order findById(int id) {
    return repository.findById(id);
  }

  public List<Order> findAll() {
    return repository.findAll();
  }

  public void add(Order order) {
    repository.add(order);
  }

  public Order update(Order order) {
    return repository.update(order);
  }
}
