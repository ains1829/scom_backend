package com.ains.myspring.repository.modules.mission.collecte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.modules.mission.collecte.Detailcollecte;

public interface DetailcollecteRepository extends JpaRepository<Detailcollecte, Integer> {
  @Query(value = "select * from detailcollecte where idcollecte = :idcollecte", nativeQuery = true)
  List<Detailcollecte> getDetailcollectesBycollecte(int idcollecte);
}
