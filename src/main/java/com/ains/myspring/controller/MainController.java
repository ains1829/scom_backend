package com.ains.myspring.controller;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ains.myspring.models.Administration;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.models.jsontoclass.RegisterUser;
import com.ains.myspring.security.function.AuthentificationService;
import com.ains.myspring.services.AdministrationService;

@RestController
@RequestMapping("/hello")
public class MainController {
  @Autowired
  private AuthentificationService _serviceAuth;

  @Autowired
  private AdministrationService _serviceAdmin;

  @GetMapping("/world")
  public String getHello() {
    return "Hello world";
  }

  @GetMapping("/myfriends")
  public Administration getMyFriends() {
    return _serviceAdmin.getAdminByEmail("andyrakotonavalona0@gmail.com").get();
  }

  @PostMapping("/inscription")
  public ResponseEntity<?> Inscription(@RequestBody RegisterUser register) {
    try {
      _serviceAuth.Inscription(register);
      return ResponseEntity.status(HttpStatus.CREATED).body("INSCRIPTION");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR");
    }
  }

  @PostMapping("/authentification")
  public ResponseEntity<?> Auth(@RequestBody AuthUser userform) {
    System.out.println("salut my friends");
    try {
      _serviceAuth.authenticate(userform);
      return ResponseEntity.status(HttpStatus.OK).body(_serviceAuth.authenticate(userform));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
