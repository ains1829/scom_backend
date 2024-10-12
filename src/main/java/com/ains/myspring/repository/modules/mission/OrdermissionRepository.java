package com.ains.myspring.repository.modules.mission;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ains.myspring.models.modules.mission.Ordermission;

public interface OrdermissionRepository extends JpaRepository<Ordermission, Integer> {
  @Query(value = "Select * from ordermission where status_validation = :statu and (context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%') order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionValiderOrSupprimerstatus(int statu, String text, Pageable page);

  @Query(value = "Select * from ordermission where (status_validation = 10 or status_validation = 0) and (context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%') order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionNovaliderstatus(String text, Pageable page);

  @Query(value = "Select * from ordermission where dateorderend is not null and status_validation = 100 order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionFinish(Pageable page);

  @Query(value = "Select * from ordermission where status_validation = 100 dateorderend is null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionMissionNotFinish(Pageable page);

  @Query(value = "Select * from ordermission where context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%' order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAll(String text, Pageable page);

  @Query(value = "Select * from ordermission where lower(motifs) = lower(:motif)", nativeQuery = true)
  Page<Ordermission> getOrdermissionSearchbyMotifs(String motif, Pageable page);

  @Query(value = "select * from ordermission where numeroserie = :numero_serie ", nativeQuery = true)
  Optional<Ordermission> getOrdermissionByNumeroSerie(@Param("numero_serie") String numero);

  @Query(value = "Select * from ordermission where (context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%') and idregion = :region order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAllByDrDt(String text, @Param("region") int id, Pageable page);

  @Query(value = "Select * from ordermission where status_validation = :statu and (context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%') and idregion = :region order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionValiderOrSupprimerForDrDt(int statu, @Param("region") int id, String text,
      Pageable page);

  @Query(value = "Select * from ordermission where (status_validation = 0 or status_validation = 10) and (context ilike '%'||:text||'%' or motifs ilike '%'||:text||'%' or nomsociete ilike '%'||:text||'%' or numeroserie ilike '%'||:text||'%') and idregion = :region order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionNovaliderForDrDt(@Param("region") int id, String text, Pageable page);

  @Query(value = "Select * from ordermission where idregion = :region and status_validation = 100 and extract(year from dateorder) = :year order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getMissionAllByDrDt(@Param("region") int id, int year, Pageable page);

  @Query(value = "Select * from ordermission where idregion = :region and status_validation = 100 and extract(year from dateorder) = :year order by dateorder desc", nativeQuery = true)
  List<Ordermission> getMissionAllCalendarByDrDt(@Param("region") int id, int year);

  @Query(value = "Select * from ordermission where idregion = :region and status_validation = 100 and extract(year from dateorder) = :year and dateorderend is not null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getMissionFinishByDrDt(@Param("region") int id, int year, Pageable page);

  @Query(value = "Select * from ordermission where idregion = :region and status_validation = 100 and extract(year from dateorder) = :year and dateorderend is null order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getMissionNotFinishByDrDt(@Param("region") int id, int year, Pageable page);

  @Query(value = "Select * from ordermission where idequipe = :equipe and status_validation = 100 order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionAllByEquipe(int equipe, Pageable pageable);

  @Query(value = "Select * from ordermission where idequipe = :equipe and status_validation = 100 and dateorderend is not null  order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionFinishByEquipe(int equipe, Pageable pageable);

  @Query(value = "Select * from ordermission where idequipe = :equipe and status_validation = 100 and dateorderend is null  order by dateorder desc", nativeQuery = true)
  Page<Ordermission> getOrdermissionNotFinishByEquipe(int equipe, Pageable pageable);

}
