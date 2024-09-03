package com.ains.myspring.repository.modules.mission;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ains.myspring.models.modules.mission.Ordermission;

public interface OrdermissionRepository extends JpaRepository<Ordermission, Integer> {
  @Query(value = "Select * from ordermission where status_validation = :status order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionFilterstatus(int status, Pageable page);

  @Query(value = "Select * from ordermission where dateorderend is not null and status_validation = 100 order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionFinish(Pageable page);

  @Query(value = "Select * from ordermission where status_validation = 100 dateorderend is null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionNotFinish(Pageable page);

  @Query(value = "Select * from ordermission order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAll(Pageable page);

  @Query(value = "Select * from ordermission where lower(motifs) = lower(:motif)", nativeQuery = true)
  Page<Ordermission> getOrdermissionSearchbyMotifs(String motif, Pageable page);

  @Query(value = "select * from ordermission where numeroserie = :numero_serie ", nativeQuery = true)
  Optional<Ordermission> getOrdermissionByNumeroSerie(@Param("numero_serie") String numero);

  @Query(value = "Select * from ordermission where idregion = :region order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAllByDrDt(@Param("region") int id, Pageable page);

  @Query(value = "Select * from ordermission where idregion = :region and status_validation = 100 order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getMissionAllByDrDt(@Param("region") int id, Pageable page);

  @Query(value = "Select * from ordermission where idequipe = :equipe and status_validation = 100 order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionByEquipe(int equipe, Pageable pageable);

}
