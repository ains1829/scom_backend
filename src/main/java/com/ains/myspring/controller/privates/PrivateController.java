package com.ains.myspring.controller.privates;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.Token;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.JsonAdministration;
import com.ains.myspring.models.jsontoclass.JsonSociete;
import com.ains.myspring.models.jsontoclass.equipe.EquipeJson;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.services.admin.AccountService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/scomadminstration")
@RestController
@CrossOrigin("*")
public class PrivateController {
  @Autowired
  private AccountService _serviceAccount;
  @Autowired
  private SocieteService _serviceSociete;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private DistrictService _serviceDistrict;
  @Autowired
  private Token token_email;

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
      District district = _serviceDistrict.getById(societe.getIddistrict());
      Societe newSociete = new Societe(societe.getNamesociete(), societe.getDescription(), societe.getNif(),
          societe.getStat(), district.getRegion(), district, societe.getAddresse(),
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

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/equipebyregion")
  public ResponseEntity<?> Equipebyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceEquipe.getEquipeByRegion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getdistrictbyregion")
  public ResponseEntity<?> getDistrictbyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceDistrict.getDistrictByregion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getSocietebyregion")
  public ResponseEntity<?> getSocietebyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceSociete.getSocietebyregion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getSocietebyregionpagination")
  public ResponseEntity<?> getSocietebyregionpagination(
      @RequestParam(name = "page", defaultValue = "0") int pagenumber) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    Page<Societe> societe = _serviceSociete.getSocietebyregion(administration.get().getRegion().getIdregion(),
        pagenumber);
    HashMap<String, Object> mapping = new HashMap<>();
    mapping.put("hasnext", societe.hasNext());
    mapping.put("hasprevious", societe.hasPrevious());
    mapping.put("data", societe.getContent());
    mapping.put("nombrepage", societe.getTotalPages());
    mapping.put("page", pagenumber);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getmissionnairebyregion")
  public ResponseEntity<?> getMissionnaireByregion(@RequestParam(defaultValue = "0") int pagenumber) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Administration> missionnaire = _serviceAdministration.getMissionnaireByregion(pagenumber,
        administration.get().getRegion().getIdregion());
    mapping.put("hastnext", missionnaire.hasNext());
    mapping.put("hastprevious", missionnaire.hasPrevious());
    mapping.put("data", missionnaire.getContent());
    mapping.put("nombrepage", missionnaire.getTotalPages());
    mapping.put("page", pagenumber);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

}
