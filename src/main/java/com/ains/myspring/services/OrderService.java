package com.ains.myspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.Orders;
import com.ains.myspring.repository.OrderRepository;

@Service
public class OrderService {
  @Autowired
  private OrderRepository _context_order;

  public List<Orders> getOrders() {
    return _context_order.getAllOrders();
  }
}
