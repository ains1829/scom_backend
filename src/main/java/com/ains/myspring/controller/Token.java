package com.ains.myspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ains.myspring.security.config.JwtService;

@Service
public class Token {
  @Autowired
  private JwtService jwt;

  public String getEmailUserByToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    String email_chef = jwt.getEmailByToken(jwtToken);
    return email_chef;
  }

  public String getRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    List<String> role = jwt.getRolesByToken(jwtToken);
    return role.get(0);
  }
}
