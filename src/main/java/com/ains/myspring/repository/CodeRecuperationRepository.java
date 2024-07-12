package com.ains.myspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.Coderecuperation;
import org.springframework.transaction.annotation.Transactional;

public interface CodeRecuperationRepository extends JpaRepository<Coderecuperation, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE coderecuperation set isexpired = true where dateexpiration < now()")
  void UpdateCodeExpired();

  @Modifying
  @Transactional
  @Query("UPDATE coderecuperation set isavailable = false where email=:email and code =:code")
  void UpdateCodeToNotAvailable(String email, int code);

  @Query("Select * from coderecuperation where email=:email and code =:code")
  Optional<Coderecuperation> getCodeByEmailandCode(String email, int code);
}
