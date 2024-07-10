package com.ains.myspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ains.myspring.models.Administration;

public interface AdministrationRepository extends JpaRepository<Administration, Integer> {
  @Query(value = "Select * from administration where email = :emailadmin limit 1", nativeQuery = true)
  Optional<Administration> getAdministrationByEmail(@Param("emailadmin") String email_admin);
}
