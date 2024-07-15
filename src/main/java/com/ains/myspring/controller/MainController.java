package com.ains.myspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
      return ResponseEntity.status(HttpStatus.OK).body(_ServieAuth.login(user));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/generatecode")
  public ResponseEntity<?> GenerateCode(@RequestParam("email") String email) {
    Optional<Administration> getAdminByEmail = _serviceAdmin.getAdministrationByEmail(email);
    int code = new Fonction().generateCode();
    if (getAdminByEmail.isPresent()) {
      String subjet = "Code de verification";
      String body = "Veuillez ne pas partager le code de vérification reçu : " + code;
      try {
        List<String> destinataire = new ArrayList<>();
        destinataire.add(email);
        email_service.sendEmail(destinataire, subjet, body);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
      _ServiceCode.newCodeOfRecuperation(code, email);
      return ResponseEntity.status(HttpStatus.OK).body("Check your email");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Wrong email please verify or you aren't an administrator");
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
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password not same");
    }
    try {
      _ServiceCode.UpdateCodeToNotAvailable(requestnewpasswod.getEmail(), requestnewpasswod.getCode());
      _ServiceAccount.UpdatePassword(requestnewpasswod.getEmail(), requestnewpasswod.getPassword());
      return ResponseEntity.status(HttpStatus.OK).body("Reset password succes");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
