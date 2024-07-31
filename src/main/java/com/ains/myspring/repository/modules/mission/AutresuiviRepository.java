package com.ains.myspring.repository.modules.mission;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.mission.Autresuivi;

public interface AutresuiviRepository extends JpaRepository<Autresuivi, Integer> {
  @Query(value = "Select * from autresuivi where idordermission = :ordre", nativeQuery = true)
  Optional<Autresuivi> getAutreSuivibyOrdremission(int ordre);
}
