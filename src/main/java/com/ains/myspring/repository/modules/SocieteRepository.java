package com.ains.myspring.repository.modules;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.Societe;

public interface SocieteRepository extends JpaRepository<Societe, Integer> {
  @Query(value = "Select count(*) as n from societe where nif = :nif", nativeQuery = true)
  int NifIsExist(String nif);

  @Query(value = "Select count(*) as n from societe where stat = :stat", nativeQuery = true)
  int StatIsExist(String stat);

  @Query(value = "Select count(*) as n from societe where numerofiscal = :numerofiscal", nativeQuery = true)
  int FiscalIsExist(String numerofiscal);

  @Query(value = "Select * from  societe where idregion is null and iddistrict is null", nativeQuery = true)
  Optional<Societe> getSocieteNotFound();

  @Query(value = "Select * from societe where idregion =:region", nativeQuery = true)
  List<Societe> getSocieteByRegion(int region);

  @Query(value = "Select * from societe where (namesociete ilike '%'||:text||'%' or description ilike '%'||:text||'%') and idregion =:region and societeactive=true", nativeQuery = true)
  Page<Societe> getSocieteByregion(int region, String text, Pageable page);

  @Query(value = "Select * from societe where (namesociete ilike '%'||:text||'%' or description ilike '%'||:text||'%') and societeactive=true and idregion is not null", nativeQuery = true)
  Page<Societe> getSocieteglobal(String text, Pageable page);
}