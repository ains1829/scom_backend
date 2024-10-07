package com.ains.myspring.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ains.myspring.models.admin.Administration;

public interface AdministrationRepository extends JpaRepository<Administration, Integer> {
  @Query(value = "Select * from administration where email = :email and haveaccount = true and isactive = true", nativeQuery = true)
  Optional<Administration> getAdministrationByEmail(@Param("email") String email);

  @Query(value = "Select count(*)  from administration where email =:email and isactive = true", nativeQuery = true)
  int CheckAdministrationByEmail(@Param("email") String email);

  @Query(value = "Select count(*) from administration where telephone =:telephone and isactive = true", nativeQuery = true)
  int CheckAdministrationByTelephone(@Param("telephone") String telephone);

  @Query(value = "Select count(*) from administration where matricule =:matricule and isactive = true", nativeQuery = true)
  int CheckAdministrationByMatricule(@Param("matricule") String matricule);

  @Query(value = "select * from administration where (idprofil = 6 or idprofil =7) and isactive = true", nativeQuery = true)
  Page<Administration> getMissionnaire(Pageable page);

  @Query(value = "select * from administration where idprofil > 3 and isactive = true and idregion = :region and (nameadministration ilike '%'||:text||'%' or matricule ilike '%'||:text||'%')", nativeQuery = true)
  Page<Administration> getMissionnaireByRegion(int region, Pageable page, String text);

  @Query(value = "select administration.* from administration left join (select detailequipe.idadministration  from DETAILEQUIPE join EQUIPE on (EQUIPE.idequipe = DETAILEQUIPE.idequipe) where EQUIPE.isactive = true and equipe.idregion = :region) as v_test on (v_test.idadministration = administration.idadministration) where Extract(year from age(birthday , current_date)) < 60 and administration.idregion = :region and v_test.idadministration is null and administration.idprofil > 3", nativeQuery = true)
  List<Administration> getAdministrationNoEquipe(int region);

  @Query(value = "select * from administration where idprofil <= 3 and isactive = true order by idprofil", nativeQuery = true)
  List<Administration> getAdministration();

  @Query(value = "select * from administration where idregion = :region and idprofil > 3 and (matricule ilike '%'||:text||'%' or  email ilike '%'||:text||'%' or nameadministration ilike '%'||:text||'%') and isactive = true order by idprofil", nativeQuery = true)
  Page<Administration> getDirecteurRT(String text, int region, Pageable pageable);

  @Query(value = "select * from administration where idprofil = 1 and isactive", nativeQuery = true)
  Administration getSg();

  @Query(value = "select * from administration where idprofil = 2 and isactive", nativeQuery = true)
  Administration getDg();

  @Query(value = "Select * from administration where idprofil = :idprofil", nativeQuery = true)
  List<Administration> getAdministrationsByprofil(int idprofil);

  @Query(value = "select equipe.isactive from equipe join detailequipe on (detailequipe.idequipe = equipe.idequipe) where detailequipe.idadministration = :admin", nativeQuery = true)
  boolean AdminInEquipe(int admin);

  @Query(value = "Select count(*) from administration where idprofil = 4 and idregion=:region and isactive ", nativeQuery = true)
  int CountDr(int region);

  @Query(value = "Select count(*) from administration where idprofil = 8 and idregion=:region  and isactive ", nativeQuery = true)
  int CountDT(int region);

  @Query(value = "Select count(*) from administration where idprofil = 3 and isactive", nativeQuery = true)
  int CountDsi();

  @Query(value = "select * from administration where idprofil = 4 and idregion = :region and isactive", nativeQuery = true)
  Administration Drbyregion(int region);
}
