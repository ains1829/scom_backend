package com.ains.myspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.models.jsontoclass.RegisterUser;
import com.ains.myspring.security.function.AuthentificationService;
import com.ains.myspring.services.CodeService;

@RestController
@RequestMapping("/hello")
public class MainController {
  @Autowired
  private AuthentificationService _serviceAuth;
  @Autowired
  private CodeService _serviceCode;

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

  @GetMapping("/sendcode")
  public ResponseEntity<?> LostPassword(@RequestParam("email") String email) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(_serviceCode.RenvoyeCode(email));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
