package com.ains.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ains.myspring.models.Coderecuperation;

public interface CoderecuperationRepository extends JpaRepository<Coderecuperation, Integer> {
  @Query(value = "Select * from coderecuperation where code = :code and email = :email and isexpired = false", nativeQuery = true)
  Coderecuperation getCodeRecuperation(@Param("code") int code, @Param("email") String email);
}
