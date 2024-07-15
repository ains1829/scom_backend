package com.ains.myspring.repository.modules.equipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ains.myspring.models.modules.equipe.Detailequipe;

public interface DetailequipeRepository extends JpaRepository<Detailequipe, Integer> {
  @Query(value = "select count(*) as n from detailequipe join equipe on (equipe.idequipe = detailequipe.idequipe) where detailequipe.idadministration = :idadministration and equipe.isactive = true", nativeQuery = true)
  int MembreHaveEquipe(int idadministration);

  @Query(value = "Select count(*) as n from administration where idadministration = :idadministration and isactive = true", nativeQuery = true)
  int PersonExist(int idadministration);

  @Modifying
  @Transactional
  @Query(value = "update administration set haveaccount = false where idadministration = :idadministration and isactive = true  ", nativeQuery = true)
  void DesactiveCompteChefEquipe(int idadministration);
}
