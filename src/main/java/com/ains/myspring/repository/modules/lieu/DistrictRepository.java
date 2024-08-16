package com.ains.myspring.repository.modules.lieu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.modules.lieu.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
  @Query(value = "Select * from district where idregion = :region", nativeQuery = true)
  List<District> getDistrictByregion(int region);
}
