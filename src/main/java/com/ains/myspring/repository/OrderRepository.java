package com.ains.myspring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
  @Query(value = "Select * from orders", nativeQuery = true)
  List<Orders> getAllOrders();
}
