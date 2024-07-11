package com.ains.myspring.security.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.Administration;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.models.jsontoclass.RegisterUser;
import com.ains.myspring.security.JwtService;
import com.ains.myspring.services.AdministrationService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthentificationService {
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AdministrationService _ServiceAdministration;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public String authenticate(AuthUser formUser) throws Exception {
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(formUser.getEmail(), formUser.getPassword()));
      var user = _ServiceAdministration.getAdminByEmail(formUser
          .getEmail())
          .orElseThrow();
      var jwtToken = jwtService.generateToken(user);
      return jwtToken;
    } catch (AuthenticationException e) {
      throw new Exception("mot de passe incorrect");
    }
  }

  public void Inscription(RegisterUser request) throws Exception {
    try {
      _ServiceAdministration.Save(new Administration(request.getNameadministration(), request.getMatricule(),
          request.getEmail(), request.getTelephone(), passwordEncoder.encode(request
              .getMdp()),
          request.getBirthday(), request.getGender(),
          request.getAddresse(), request.getPhoto(), request.getIdprofil()));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

}
