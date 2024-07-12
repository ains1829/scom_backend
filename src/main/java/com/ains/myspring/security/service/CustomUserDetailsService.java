package com.ains.myspring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.admin.Account;
import com.ains.myspring.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private AccountRepository _ServiceAccount;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account accountperson = _ServiceAccount.getAccountbyemail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User haven't an account : " + username));
    return accountperson;
  }

}
