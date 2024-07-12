package com.ains.myspring.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ains.myspring.services.admin.AccountService;

@RequestMapping("/scomadminstration")
@RestController
public class PrivateController {
  @Autowired
  private AccountService _serviceAccount;

  @PreAuthorize("hasRole('SG') or hasRole('DSI')")
  @GetMapping("/secure-data")
  public ResponseEntity<?> getSecureData() {
    try {
      return ResponseEntity.ok("Données sécurisées accessibles uniquement aux utilisateurs avec le rôle SG");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @GetMapping("/secure-data-dsi")
  public ResponseEntity<?> getSecureDataDSI() {
    try {
      return ResponseEntity.ok("Données sécurisées accessibles uniquement aux utilisateurs avec le rôle DSI");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PutMapping("/validateaccount")
  public ResponseEntity<?> ValidateAccount(@RequestParam("idaccount") int idaccoount) {
    try {
      _serviceAccount.ValidateAccount(idaccoount);
      return ResponseEntity.status(HttpStatus.OK).body("Account validate");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
