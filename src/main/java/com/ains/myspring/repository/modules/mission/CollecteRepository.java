package com.ains.myspring.repository.modules.mission;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.mission.Collecte;

public interface CollecteRepository extends JpaRepository<Collecte, Integer> {
  @Query(value = "Select * from collecte where idordermission = :ordre", nativeQuery = true)
  Optional<Collecte> getCollecteByOrdermission(int ordre);

  @Query(value = "Select * from collecte where statu = 200 ", nativeQuery = true)
  Page<Collecte> getCollecteTovalidate(Pageable page);
}
