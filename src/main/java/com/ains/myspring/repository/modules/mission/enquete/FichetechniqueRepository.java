package com.ains.myspring.repository.modules.mission.enquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.modules.mission.enquete.Fichetechnique;

public interface FichetechniqueRepository extends JpaRepository<Fichetechnique, Integer> {
  @Query(value = "Select count(*) as n from fichetechnique where numero_reference = :ref ", nativeQuery = true)
  int IsRefExist(String ref);
}
