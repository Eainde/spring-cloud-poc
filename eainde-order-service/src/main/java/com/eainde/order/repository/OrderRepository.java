package com.eainde.order.repository;

import com.eainde.order.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class OrderRepository {
  @PersistenceContext
  private final EntityManager entityManager;

  OrderRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Order findById(int id) {
    return entityManager
        .createQuery("Select o from Order o where o.orderId=:id", Order.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public List<Order> findAll() {
    return entityManager.createQuery("Select o from Order o", Order.class).getResultList();
  }

  @Transactional
  public void add(Order order) {
    entityManager.persist(order);
  }

  @Transactional
  public Order update(Order order) {
    return entityManager.merge(order);
  }
}
