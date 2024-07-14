package com.ains.myspring.repository.modules;

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
}
