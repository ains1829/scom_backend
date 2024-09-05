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
  @Query(value = "Select * from administration where email = :email and haveaccount = true", nativeQuery = true)
  Optional<Administration> getAdministrationByEmail(@Param("email") String email);

  @Query(value = "select * from administration where (idprofil = 6 or idprofil =7) and isactive = true", nativeQuery = true)
  Page<Administration> getMissionnaire(Pageable page);

  @Query(value = "select * from administration where (idprofil = 6 or idprofil =7) and isactive = true and idregion = :region and (nameadministration ilike '%'||:text||'%' or matricule ilike '%'||:text||'%')", nativeQuery = true)
  Page<Administration> getMissionnaireByRegion(int region, Pageable page, String text);

  @Query(value = "select administration.* from administration left join (select detailequipe.idadministration  from DETAILEQUIPE join EQUIPE on (EQUIPE.idequipe = DETAILEQUIPE.idequipe) where EQUIPE.isactive = true and equipe.idregion = :region) as v_test on (v_test.idadministration = administration.idadministration) where administration.idregion = :region and v_test.idadministration is null and administration.idprofil != 1 and administration.idprofil != 2 and administration.idprofil != 3", nativeQuery = true)
  List<Administration> getAdministrationNoEquipe(int region);

  @Query(value = "select * from administration where idprofil <= 3 and isactive = true order by idprofil", nativeQuery = true)
  List<Administration> getAdministration();

  @Query(value = "select * from administration where (idprofil = 4 or idprofil = 8) and isactive = true order by idprofil", nativeQuery = true)
  Page<Administration> getDirecteurRT(Pageable pageable);
}
