package com.ains.myspring.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ains.myspring.models.modules.Typeproduct;

public interface TypeproductRepository extends JpaRepository<Typeproduct, Integer> {
  @Query(value = "Select count(*) as n from typeproduct where lower(nametypeproduct) = lower(:newtypeproduct)", nativeQuery = true)
  int TypeproductIsExist(@Param("newtypeproduct") String nameproduct);
}
