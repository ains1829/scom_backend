package com.ains.myspring.repository.modules.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.mission.Ordermission;

public interface OrdermissionRepository extends JpaRepository<Ordermission, Integer> {
  @Query(value = "Select * from ordermission where status_validation = :status order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionFilterstatus(int status, Pageable page);

  @Query(value = "Select * from ordermission where dateorderend is not null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionFinish(Pageable page);

  @Query(value = "Select * from ordermission where dateorderend is null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionNotFinish(Pageable page);

  @Query(value = "Select * from ordermission order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAll(Pageable page);

  @Query(value = "Select * from ordermission where lower(motifs) = lower(:motif)", nativeQuery = true)
  Page<Ordermission> getOrdermissionSearchbyMotifs(String motif, Pageable page);

}
