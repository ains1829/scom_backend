package com.ains.myspring.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.AuthUser;
import com.ains.myspring.models.jsontoclass.LostPassword;
import com.ains.myspring.security.service.AuthService;
import com.ains.myspring.services.CodeRecuperationService;
import com.ains.myspring.services.admin.AccountService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.function.Fonction;
import com.ains.myspring.services.function.mail.SendingMail;

@RequestMapping("/auth")
@RestController
@CrossOrigin("*")
public class MainController {
  @Autowired
  private AccountService _ServiceAccount;
  @Autowired
  private AdministrationService _serviceAdmin;
  @Autowired
  private AuthService _ServieAuth;
  @Autowired
  private SendingMail email_service;
  @Autowired
  private CodeRecuperationService _ServiceCode;

  @PostMapping("/createAccount")
  public ResponseEntity<?> CreateAccount(@RequestBody AuthUser user) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(_ServiceAccount.CreateAccount(user));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }

  @PostMapping("/authentification")
  public ResponseEntity<?> Auth(@RequestBody AuthUser user) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _ServieAuth.login(user)).Mapping());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ResponseEntity.ok(new ReturnMap(401, e.getMessage()).Mapping());
    }
  }

  @GetMapping("/generatecode")
  public ResponseEntity<?> GenerateCode(@RequestParam("email") String email) {
    Optional<Administration> getAdminByEmail = _serviceAdmin.getAdministrationByEmail(email);
    int code = new Fonction().generateCode();
    if (getAdminByEmail.isPresent()) {
      try {
        email_service.sendEmail(email, code);
      } catch (Exception e) {
        return ResponseEntity.ok(new ReturnMap(500, "Connexion failed").Mapping());
      }
      return ResponseEntity.ok(new ReturnMap(200, _ServiceCode.newCodeOfRecuperation(code, email).getCode()).Mapping());
    } else {
      return ResponseEntity
          .ok(new ReturnMap(500, "Wrong email please verify or you aren't an administrator").Mapping());
    }
  }

  @GetMapping("/validateCode")
  public ResponseEntity<?> CheckCode(@RequestParam("email") String email, @RequestParam("code") int code) {
    try {
      _ServiceCode.CodeValidate(email, code);
      return ResponseEntity.status(HttpStatus.OK).body("Code validate");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

  @PostMapping("/resetpassword")
  public ResponseEntity<?> resetPassword(@RequestBody LostPassword requestnewpasswod) {
    if (requestnewpasswod.getPassword().equals(requestnewpasswod.getConfirpassword()) == false) {
      return ResponseEntity.ok(new ReturnMap(500, "Password not same").Mapping());
    }
    try {
      _ServiceCode.UpdateCodeToNotAvailable(requestnewpasswod.getEmail(), requestnewpasswod.getCode());
      _ServiceAccount.UpdatePassword(requestnewpasswod.getEmail(), requestnewpasswod.getPassword());
      return ResponseEntity.ok(new ReturnMap(200, "Reset password succes"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }
}
