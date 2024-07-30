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

  @Query(value = "select * from administration where idprofil != 6 and idprofil !=7 and isactive = true order by idprofil asc", nativeQuery = true)
  List<Administration> getAdministrator();

  @Query(value = "select * from administration where idprofil = 6 or idprofil =7 and isactive = true", nativeQuery = true)
  Page<Administration> getMissionnaire(Pageable page);

  @Query(value = "select * from administration where idprofil = 6 or idprofil =7 and isactive = true and idregion = :region", nativeQuery = true)
  Page<Administration> getMissionnaireByRegion(int region, Pageable page);
}
