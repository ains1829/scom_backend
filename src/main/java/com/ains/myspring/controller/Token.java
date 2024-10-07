package com.ains.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.security.config.JwtService;
import com.ains.myspring.services.admin.AdministrationService;

@Service
public class Token {
  @Autowired
  private JwtService jwt;
  @Autowired
  private AdministrationService _serviceAdministration;

  public String getEmailUserByToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    String email_chef = jwt.getEmailByToken(jwtToken);
    return email_chef;
  }

  public HashMap<String, Object> getRole() throws Exception {
    HashMap<String, Object> map_user = new HashMap<>();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    String email_chef = jwt.getEmailByToken(jwtToken);
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email_chef);
    List<String> role = jwt.getRolesByToken(jwtToken);
    map_user.put("role", role.get(0));
    map_user.put("user", administration.get());
    return map_user;
  }
}
