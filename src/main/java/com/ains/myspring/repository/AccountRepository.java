package com.ains.myspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ains.myspring.models.admin.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
  @Query(value = "Select * from account where email =:email and accountvalidate = :choice", nativeQuery = true)
  Optional<Account> getAccountValidatebyemail(@Param("email") String email, @Param("choice") boolean isvalidate);

  @Query(value = "Select * from account where email =:email", nativeQuery = true)
  Optional<Account> getAccountbyemail(@Param("email") String email);

  @Query(value = "Select * from account where email =:email and isactive", nativeQuery = true)
  Optional<Account> getAccountActive(@Param("email") String email);
}
