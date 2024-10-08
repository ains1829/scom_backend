package com.ains.myspring.services.admin;

import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.admin.Account;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.repository.AccountRepository;
import com.ains.myspring.security.error.AccesException;

@Service
public class AccountService {
  @Autowired
  private AccountRepository _contextaccount;
  @Autowired
  private AdministrationService _serviceAdmin;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public Page<Account> getListAccountValidate(int pagenumber) {
    int size = 20;
    Pageable pageable = PageRequest.of(pagenumber, size);
    return _contextaccount.getListAccountValidate(pageable);
  }

  public Page<Account> getListAccountNoValidate(int pagenumber) {
    int size = 20;
    Pageable pageable = PageRequest.of(pagenumber, size);
    return _contextaccount.getListAccountNoValidate(pageable);
  }

  public Optional<Account> getAccountValidatebyemail(String email, boolean isvalidate) {
    return _contextaccount.getAccountValidatebyemail(email, isvalidate);
  }

  public Optional<Account> getAccountbyemail(String email) {
    return _contextaccount.getAccountbyemail(email);
  }

  public Optional<Account> getAccountActive(String email) {
    return _contextaccount.getAccountActive(email);
  }

  public Account CreateAccount(AuthUser createuser) throws Exception {
    Optional<Administration> administrationUser = _serviceAdmin.getAdministrationByEmail(createuser.getEmail());
    if (administrationUser.isPresent()) {
      if (getAccountbyemail(createuser.getEmail()).isPresent()) {
        throw new AccesException("This email have already an account");
      }
      Account new_account = new Account(administrationUser.get(),
          createuser.getEmail(),
          passwordEncoder.encode(createuser.getPassword()),
          administrationUser.get().getProfil(), new Date(System.currentTimeMillis()));
      if (_contextaccount.AccountIsChef(administrationUser.get().getIdadministration()) == 1) {
        new_account.setChefequipe(true);
      }
      return _contextaccount.save(new_account);

    } else {
      throw new AccesException("Wrong email please verify or you are not an administrator");
    }
  }

  public Account UpdatePassword(String email, String newpassword) throws Exception {
    Optional<Account> accountLost = _contextaccount.getAccountbyemail(email);
    if (accountLost.isPresent()) {
      accountLost.get().setPassword(passwordEncoder.encode(newpassword));
      return _contextaccount.save(accountLost.get());
    } else {
      throw new AccesException("Wrong email please verify or you are not an administrator");
    }
  }

  public Account ValidateAccount(int idaccount) throws Exception {
    Optional<Account> account = _contextaccount.findById(idaccount);
    if (account.isPresent()) {
      account.get().setAccountvalidate(true);
      account.get().setDatevalidate(new Date(System.currentTimeMillis()));
      return _contextaccount.save(account.get());
    } else {
      throw new AccesException("Account not found");
    }
  }

  public Account AccountDisabled(String email) {
    Optional<Account> getAccount = getAccountbyemail(email);
    if (getAccount.isPresent()) {
      getAccount.get().setIsactive(false);
      return _contextaccount.save(getAccount.get());
    }
    return null;
  }
}
