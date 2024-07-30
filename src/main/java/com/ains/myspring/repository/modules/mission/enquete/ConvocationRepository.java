package com.ains.myspring.repository.modules.mission.enquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.mission.enquete.Convocation;

public interface ConvocationRepository extends JpaRepository<Convocation, Integer> {
  @Query(value = "select count(*) as n from convocation where numeroconvocation = :ref ", nativeQuery = true)
  int RefConvocationExist(String ref);
}
