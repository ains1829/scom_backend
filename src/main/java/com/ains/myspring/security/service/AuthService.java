package com.ains.myspring.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.admin.Account;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.repository.AccountRepository;
import com.ains.myspring.security.config.JwtService;

@Service
public class AuthService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtService _ServiceJWT;
  @Autowired
  private AccountRepository _context_repository;

  public HashMap<String, Object> login(AuthUser loginForm) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
      Optional<Account> account_active = _context_repository.getAccountActive(loginForm.getEmail());
      if (account_active.isEmpty()) {
        throw new Exception("Wrong password");
      }
      Optional<Account> account = _context_repository.getAccountValidatebyemail(loginForm.getEmail(), true);
      if (account.isPresent()) {
        List<String> role = new ArrayList<>();
        role.add(account.get().getProfil().getNameprofil());
        String token = _ServiceJWT.createToken(loginForm.getEmail(), role);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("role", role.get(0));
        return map;
      } else {
        throw new Exception("Wait the administrator validate your account");
      }
    } catch (AuthenticationException e) {
      throw new Exception("Wrong password");
    }
  }
}
