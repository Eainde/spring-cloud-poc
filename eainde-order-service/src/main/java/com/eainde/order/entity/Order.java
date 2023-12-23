package com.eainde.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_ORDER")
public class Order {
  @Id
  @Column(name = "order_id")
  private int orderId;

  @Column(name = "description")
  private String description;

  @Column(name = "country")
  private String country;

  @Column(name = "quantity")
  private int quantity;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
