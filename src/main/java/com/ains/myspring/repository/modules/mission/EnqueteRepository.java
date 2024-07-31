package com.ains.myspring.repository.modules.mission;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.modules.mission.Enquete;

public interface EnqueteRepository extends JpaRepository<Enquete, Integer> {
  @Query(value = "Select * from enquete where idordermission = :ordre", nativeQuery = true)
  Optional<Enquete> getEnqueteByOrderMission(int ordre);
}
