package com.ains.myspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ains.myspring.models.Administration;

public interface AdministrationRepository extends JpaRepository<Administration, Integer> {
  @Query(value = "Select * from administration where email = :emailadmin", nativeQuery = true)
  Optional<Administration> getAdministrationByEmail(@Param("emailadmin") String email_admin);
}
