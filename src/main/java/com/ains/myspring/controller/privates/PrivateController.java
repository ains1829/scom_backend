package com.ains.myspring.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.models.jsontoclass.JsonAdministration;
import com.ains.myspring.models.jsontoclass.JsonSociete;
import com.ains.myspring.models.jsontoclass.equipe.EquipeJson;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.services.admin.AccountService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.equipe.EquipeService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/scomadminstration")
@RestController
public class PrivateController {
  @Autowired
  private AccountService _serviceAccount;
  @Autowired
  private SocieteService _serviceSociete;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private AdministrationService _serviceAdministration;

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

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/newperson")
  public ResponseEntity<?> NewAdministration(@RequestPart("photo") MultipartFile photo, HttpServletRequest request) {
    try {
      JsonAdministration admin = new JsonAdministration(request);
      return ResponseEntity.status(HttpStatus.OK)
          .body(_serviceAdministration.CreateNewAdministration(photo, admin));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/newSociete")
  public ResponseEntity<?> NewSociete(@RequestBody JsonSociete societe) {
    try {
      Societe newSociete = new Societe(societe.getNamesociete(), societe.getDescription(), societe.getNif(),
          societe.getStat(), societe.getIdregion(), societe.getIddistrict(), societe.getAddresse(),
          societe.getResponsable(), societe.getTelephone(), societe.getNumerofiscal());
      return ResponseEntity.status(HttpStatus.OK).body(_serviceSociete.AddNewSociete(newSociete));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('DR')")
  @PostMapping("/createEquipe")
  public ResponseEntity<?> NewEquipe(@RequestBody EquipeJson jsonEquipe) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(_serviceEquipe.CreateEquipe(jsonEquipe.getEquipe(), jsonEquipe.getMembre()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
