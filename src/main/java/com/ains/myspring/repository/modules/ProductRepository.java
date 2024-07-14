package com.ains.myspring.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.modules.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Query(value = "Select count(*) as n from product where lower(nameproduct) = lower(:nameproduct)", nativeQuery = true)
  int ProductIsExist(String nameproduct);

}
