package com.ains.myspring.repository.modules.equipe;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.equipe.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
  @Query(value = "Select count(*) as n from equipe where lower(nameequipe) = lower(:nameequipe) and idregion =:region and isactive = true ", nativeQuery = true)
  int NameEquipeIsExist(String nameequipe, int region);

  @Query(value = "Select * from equipe where idequipe = :id and idregion = :region", nativeQuery = true)
  Optional<Equipe> getEquipeByid(int id, int region);

  @Query(value = "Select * from equipe where idadministration = :chef and isactive ", nativeQuery = true)
  Optional<Equipe> getEquipeByChef(int chef);

  @Query(value = "Select * from equipe where idregion =:region and isactive", nativeQuery = true)
  List<Equipe> getEquipeByRegion(int region);

  @Query(value = "select count(*) as n  from equipe join ordermission on (equipe.idequipe = ordermission.idequipe) where equipe.idequipe = :idequipe and status_validation != 100 ", nativeQuery = true)
  int EquipeinPeddingMission(int idequipe);
}