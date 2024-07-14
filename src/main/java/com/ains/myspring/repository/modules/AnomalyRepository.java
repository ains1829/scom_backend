package com.ains.myspring.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ains.myspring.models.modules.Anomaly;

public interface AnomalyRepository extends JpaRepository<Anomaly, Integer> {
  @Query(value = "Select count(*) as n from anomaly where lower(nameanomaly) = lower(:anomaly)", nativeQuery = true)
  int AnomlyIsExist(@Param("anomaly") String anomaly);
}
