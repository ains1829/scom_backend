package com.ains.myspring.repository.modules.equipe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.equipe.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
  @Query(value = "Select count(*) as n from equipe where lower(nameequipe) = lower(:nameequipe) and idregion =:region and isactive = true ", nativeQuery = true)
  int NameEquipeIsExist(String nameequipe, int region);

  @Query(value = "Select * from equipe where idequipe = :id and idregion = :region", nativeQuery = true)
  Optional<Equipe> getEquipeByid(int id, int region);

  @Query(value = "elect * from equipe where idadministration = :chef and isactive ", nativeQuery = true)
  Optional<Equipe> getEquipeByChef(int chef);
}